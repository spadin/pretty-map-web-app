(form-to [:post "/"]
  (text-area "raw"
   (:pretty-data *view-context*))

  (submit-button "Pretty Print"))
