(ns url-shortener.config
  (:require [url-shortener.usecases :as uc]
            [url-shortener.datomicadapter :as da]))

(defn hash-url-and-save [url]
  (uc/hash-url-and-save da/save-to-datomic url))

(defn get-original-url [url]
  (uc/get-original-url da/get-from-datomic url))