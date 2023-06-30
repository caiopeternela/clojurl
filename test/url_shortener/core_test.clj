(ns url-shortener.core-test
  (:require [clojure.test :as test]
            [url-shortener.usecases :as uc]))

(def urlmap (atom {}))

(defn save-to-urlmap
  [hash url]
  (swap! urlmap assoc hash url))

(defn get-from-urlmap
  [hash]
  (get @urlmap hash))

(def long-url "http://this-is-a-very-long-url.com/oh-yes-it-is")

(test/deftest test-use-cases
  (test/testing "Hash, save, and retrieve long url"
    (let [hash (uc/hash-url-and-save save-to-urlmap long-url)]
    (test/is (= (uc/get-original-url get-from-urlmap hash) long-url)))))
