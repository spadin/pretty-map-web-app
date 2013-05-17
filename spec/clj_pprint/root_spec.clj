(ns clj-pprint.root-spec
  (:use
    [speclj.core]
    [joodo.spec-helpers.controller]
    [clj-pprint.root]))

(describe "clj-pprint"
  (with-mock-rendering)
  (with-routes clj-pprint-routes)
  (with default-params {:raw "{:test :test}"})

  (it "handles home page"
    (let [result (do-get "/")]
      (should= 200 (:status result))
      (should= "index" @rendered-template)))

  (it "responds to the root route"
    (let [response (do-post "/" :params @default-params)]
      (should= "index" @rendered-template)
      (should= 200 (:status response))
      (should-not (nil? (:pretty-data @rendered-context)))
      (should-contain "{:test :test}" (:pretty-data @rendered-context))))

  (it "catches bad data"
    (let [response (do-post "/" :params {:raw "[:test :test}"})]
      (should-contain "bad data" (:pretty-data @rendered-context)))))
