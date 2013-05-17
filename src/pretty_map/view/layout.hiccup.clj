(doctype :html5)
[:html
 [:head
  [:meta {:http-equiv "Content-Type" :content "text/html" :charset "iso-8859-1"}]
  [:title "Pretty Clojure Maps"]
  (include-css "/stylesheets/pretty_map.css")]
 [:body
  (eval (:template-body joodo.views/*view-context*))
]]
