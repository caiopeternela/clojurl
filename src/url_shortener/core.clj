(ns url-shortener.core
  (:require [ring.adapter.jetty :as ring-jetty]
            [url-shortener.routes :as routes])
  (:gen-class))

(defn start []
  (ring-jetty/run-jetty routes/app {:port  3000
                             :join? false}))

(defn -main
  [& args]
  (start))
