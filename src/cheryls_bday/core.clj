(ns cheryls-bday.core
  (:gen-class)
  (:require [clojure.string :as str]
            [clojure.core.reducers :as r]
            [clojure.core.logic.fd :as fd])
  (:refer-clojure :exclude [==])
  (:use [clojure.core.logic])
)



(def dates ["May 15" "May 16" "May 19"
            "June 17"    "June 18"
            "July 14"    "July 16"
            "August 14"  "August 15"  "August 17"])

(defn month
  [date]
  (first (str/split date #" ")))

(defn day
  [date]
  (last (str/split date #" ")))

(defn tell
  ;;
  ;; given a month or day filters all dates of that month or day
  ;; 
  [part]
  (filter #(.contains % part) dates))

(defn know
  ;;
  ;; if there is only one possible date then its true 
  ;; 
  [possible-dates]
  (= (count possible-dates) 1))
    
(defmacro person-view
  [person day-month dates]
  `(filter #(~person (~day-month %)) ~dates))

(defn albert
  [mon]
  ;;
  ;; albert's view given a month he does not know
  ;; but for those days he know bernard does not know either
  ;;
  (let [ possible-dates (tell mon)]
    ( and ( not (know possible-dates))
          (= (count (filter #(know (tell (day %))) possible-dates)) 0))))

(defn bernard
  ;;
  ;; bernard's view, given a day he does not know
  ;; but if he applies albert's view he knows
  ;;
  [day]
  (let [ at-first (tell day)]
    ( and ( not (know at-first))
          ( know (filter #(albert (month %)) at-first)))))
          
(defn albert-also
  ;;
  ;; after bernard's view albert also knows
  ;; 
  ;;
  [mon]
  (let [ possible-dates (tell mon)]
    (know (filter #(bernard (day %)) possible-dates))))

(defn cheryls-birthday
  ;;
  ;; simple answer using cascading filters
  ;;
  []
  (first (filter #(albert-also (month %))
          (filter #(bernard (day %))
                  (filter #(albert (month %)) dates)))))

(defn cheryls-birthday-alt
  ;; alternate answer using ->>
  []
  (first ( ->> dates
              (filter #(albert (month %)))
              (filter #(bernard (day %)))
              (filter #(albert-also (month %)))
              )))

(defn cheryls-birthday-alt-2
  ;; alternate answer using ->>
  []
  (first ( ->> dates
              (person-view albert month)
              (person-view bernard day)
              (person-view albert-also month))))
  
(defn cheryls-birthday-logic
  ;; alternate answer using core.logic
  []
 (first  (run* [date]
           (membero date (filter #(albert (month %)) dates))
           (membero date (filter #(bernard (day %)) dates))
           (membero date (filter #(albert-also (month %)) dates))
           )))

(defn -main
  "Solve Cheryl's Birthday problem"
  [& args]
  (println "Cheryl's Birthday is on:" (cheryls-birthday-alt-2)))
