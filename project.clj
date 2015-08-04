(defproject cheryls-bday "0.1.0-SNAPSHOT"
  :description "A solution to Cheryl's Birthday problem in clojure"
  :url "http://example.com/FIXME"
  :license {:name "Unlicensed"
            :url "http://unlicense.org/"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/core.logic "0.8.10"]]
  :main ^:skip-aot cheryls-bday.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
