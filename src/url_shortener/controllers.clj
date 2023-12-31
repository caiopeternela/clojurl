(ns url-shortener.controllers
  (:require [url-shortener.config :as cfg]))

(defn shorten-url [{body :body-params}]
  (let [hash-key (cfg/hash-url-and-save (get body :url))]
    {:status 200
     :body {:hash-key hash-key}}))

(defn redirect-to-original [{{:keys [hash-id]} :path-params}]
  (let [original-url (cfg/get-original-url hash-id)]
    {:status 301
     :headers {"Original" original-url}}))

(defn homepage []
  {:status 200
   :body "Clojurl - Clojure & Datomic URL shortener"})