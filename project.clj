(defproject pretty-map "0.0.1"
  :description "A simple stand-alone webapp"
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [joodo "1.1.2"]]
  :min-lein-version "2.0.0"

  :joodo-root-namespace pretty-map.root

  :profiles {:dev {:dependencies [[speclj "2.5.0"]]}}
  :test-paths ["spec/"]
  :java-source-paths ["src/"]
  :plugins [[speclj "2.5.0"]
            [joodo/lein-joodo "1.1.2"]]

  )
