(ns clojurehadoop-wordcount.core
  (:use [clojurehadoop.core])
  (:import [org.apache.hadoop.io Text LongWritable])
  (:gen-class))

(defn -main 
  [& args]
  (let [in-path (path (str (nth args 1)))
        out-path (path (str (nth args 2)))]
    (println (str "in-path: " in-path " out-path: " out-path)) 
    (run-job 
      (doto (make-job "word count")
        (textinput in-path)
        (fresh-textoutput out-path)
        (number-of-reducers 2)
        (mapper-output-types Text LongWritable) 
        (reducer-output-types Text LongWritable)
        (output-types Text LongWritable)
        (mapper '(do 
                   (import [org.apache.hadoop.io Text LongWritable]) 
                   (fn [k v ctx] 
                     (doseq [word (.split (.toString v) " ")] 
                       (.write ctx (Text. word) (LongWritable. 1))))))
        (combiner '(do 
                     (import [org.apache.hadoop.io LongWritable]) 
                     (fn [k vs ctx] 
                       (.write ctx k (LongWritable. (apply + (map #(.get %) (seq vs))))))))
        (reducer '(do 
                    (import [org.apache.hadoop.io LongWritable]) 
                    (fn [k vs ctx] 
                      (.write ctx k (LongWritable. (apply + (map #(.get %) (seq vs))))))))))))

