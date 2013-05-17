(ns pretty-map.root
  (:require
    [compojure.core :refer [defroutes GET POST]]
    [compojure.route :refer [not-found]]
    [ring.util.response :refer [redirect]]
    [joodo.middleware.request :refer [*request*]]
    [joodo.util.pretty-map :refer [pretty-map]]
    [joodo.middleware.view-context :refer [wrap-view-context]]
    [joodo.views :refer [render-template]]
    [joodo.controllers :refer [controller-router]]))

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

(defroutes pretty-map-routes
  (GET "/"  [] (render-template "index"))
  (POST "/" [] (pretty-map-it))
  (controller-router 'pretty-map.controller)
  (not-found (redirect "/")))

(def app-handler
  (->
    pretty-map-routes
    (wrap-view-context :template-root "pretty_map/view" :ns `pretty-map.view.view-helpers)))

