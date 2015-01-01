(defproject formidable-demo "0.0.1-SNAPSHOT"
  :description "Demo app for the Formidable lib"
  :url "http://formidable-demo.herokuapp.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[amalloy/ring-gzip-middleware "0.1.2"]
                 [compojure "1.1.5"]
                 [crate "0.2.5"]
                 [environ "0.3.0"]
                 [formidable "0.1.7"]
                 [hiccup "1.0.2"]
                 [org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2138"]
                 [prismatic/dommy "1.0.0"]
                 [ring/ring-core "1.2.0"]
                 [ring/ring-devel "1.2.0"]
                 [ring/ring-jetty-adapter "1.2.0"]]
  :min-lein-version "2.0.0"
  :plugins [[environ/environ.lein "0.3.0"]
            [lein-cljsbuild "1.0.1"]]
  :hooks [environ.leiningen.hooks]
  :profiles {:production {:env {:production true}}}
  :cljsbuild {:builds [{:source-paths ["src-cljs"]
                        :compiler {:output-to "resources/public/js/main.js"
                                   :optimizations :advanced
                                   :pretty-print false}
                        #_{:output-dir "resources/public/js"
                          :output-to "resources/public/js/main.js"
                          :source-map "resources/public/js/main.js.map"
                          :optimizations :none
                          :pretty-print false}}]})
