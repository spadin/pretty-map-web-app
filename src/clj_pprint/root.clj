(ns clj-pprint.root
  (:use
    [compojure.core :only (defroutes GET POST)]
    [compojure.route :only (not-found)]
    [joodo.middleware.request :refer [*request*]]
    [joodo.util.pretty-map :refer [pretty-map]]
    [joodo.middleware.view-context :only (wrap-view-context)]
    [joodo.views :only (render-template)]
    [joodo.controllers :only (controller-router)]))

(defn get-pretty-map [data]
  (try
    (pretty-map (read-string data))
    (catch Exception e
      (str data "\n;; bad data"))))

(defn pretty-map-it []
  (if (empty? (:raw (:params *request*)))
    (render-template "index")
    (let [pretty-data (get-pretty-map (:raw (:params *request*)))]
      (render-template "index" {:pretty-data pretty-data}))))

(defroutes clj-pprint-routes
  (GET "/"  [] (render-template "index"))
  (POST "/" [] (pretty-map-it))
  (controller-router 'clj-pprint.controller)
  (not-found (render-template "not_found" :template-root "clj_pprint/view" :ns `clj-pprint.view.view-helpers)))

(def app-handler
  (->
    clj-pprint-routes
    (wrap-view-context :template-root "clj_pprint/view" :ns `clj-pprint.view.view-helpers)))

