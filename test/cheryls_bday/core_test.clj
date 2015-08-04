(ns cheryls-bday.core-test
  (:require [clojure.test :refer :all]
            [cheryls-bday.core :refer :all]))

(deftest cheryls-bday-test
  (testing "Testing cheryl's b'day using cascading filters"
    (is (= "July 16" (cheryls-birthday)))))

(deftest cheryls-bday-alt-test
  (testing "Testing cheryl's b'day using ->>"
    (is (= "July 16" (cheryls-birthday-alt)))))

(deftest cheryls-bday-alt-2-test
  (testing "Testing cheryl's b'day using ->> and macro"
    (is (= "July 16" (cheryls-birthday-alt-2)))))

(deftest cheryls-bday-logic-test
  (testing "Testing cheryl's b'day using core.logic"
    (is (= "July 16" (cheryls-birthday-logic)))))
