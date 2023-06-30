(ns url-shortener.datomicadapter
  (:require [datomic.client.api :as d]))

(def cfg {:server-type :cloud
          :region "us-east-1"
          :system "datomic-url-shortener"
          :endpoint "https://fe8gqooi4i.execute-api.us-east-1.amazonaws.com"
          :creds-profile "default"})

(def client (d/client cfg))

(def conn (d/connect client {:db-name "url-shortener"}))

(def db (d/db conn))

(defn save-to-datomic
  [key value]
  (d/transact conn {:tx-data [{:url/hash key
                               :url/long value}]}))

(defn get-from-datomic
  [key]
  (let [db (d/db conn)]
  (->> (d/q '[:find (pull ?e [:url/long])
              :in $ ?hash
              :where [?e :url/hash ?hash]]
            db
            key)
      ffirst
      :url/long)))

(comment
  (d/create-database client {:db-name "url-shortener"}))

(comment
  (def url-schema [{:db/ident :url/hash
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one}
                   {:db/ident :url/long
                    :db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one}]))

(comment
  (d/transact conn {:tx-data url-schema}))

(comment
  (def first-url [{:url/hash "abcdefgh"
                   :url/long "http://this.is.a.long.url.com/yes-it-is"}]))

(comment
  (d/transact conn {:tx-data first-url}))

(comment
  (def ret (d/q '[:find (pull ?e [:url/long])
                  :in $ ?hash
                  :where [?e :url/hash ?hash]]
                db
                "hash")))

(comment
  (def long (:url/long (->> ret first first))))