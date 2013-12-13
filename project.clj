(defproject clojurehadoop-examples "0.1.0-SNAPSHOT"
  :description "examples for clojurehadoop"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main clojurehadoop-wordcount.core
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [clojurehadoop "0.1.0-SNAPSHOT"]                 
                 [org.apache.hadoop/hadoop-core "0.20.2-dev"]])
