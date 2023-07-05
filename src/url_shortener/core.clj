(ns url-shortener.core
  (:require [ring.adapter.jetty :as ring-jetty]
            [url-shortener.routes :as routes])
  (:gen-class))

(defn -main
  [& args]
  (ring-jetty/run-jetty routes/app {:port  3000
                                    :join? false}))