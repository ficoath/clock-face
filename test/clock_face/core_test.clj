(ns clock-face.core-test
  (:require [clojure.test :refer :all]
            [clock-face.core :refer :all]))

(deftest validation
  (testing "hours can not be more than 24"
    (is (not (valid? "25:00"))))
  (testing "valid value is ok"
    (is (valid? "23:59")))
  (testing "less than 2 numbers after : is invalid"
    (is (not (valid? "12:1"))))
  (testing "more than 2 numbers after : is invalid"
    (is (not (valid? "12:011"))))
  (testing "more than 2 numbers before : is invalid"
    (is (not (valid? "111:25"))))
  (testing "one number before : is ok"
    (is (valid? "1:11")))
  (testing "minutes cannot be more than 59"
    (is (not (valid? "10:60")))))

(deftest extract-hours-from-time
  (testing "extracts hour when hours is two digits"
    (is (= 12 (extract-hours "12:50"))))
  (testing "extracts hours when hours in one digit"
    (is (= 3 (extract-hours "3:20")))))

(deftest extract-minutes-from-time
  (is (= "44" (extract-minutes "10:44")))
  (is (= "01" (extract-minutes "10:01"))))

(deftest should-convert-to-twelve-hour-time
  (testing "hour values over 11 are reduced by 12"
    (is (= 10 (convert-to-twelve-hour-time 22))))
  (testing "hour values under 11 remain unchanged"
    (is (= 11 (convert-to-twelve-hour-time 11)))))

(deftest should-round-minutes-for-basic-analog-time
  (testing "23 minutes rounds to 20"
    (is (= "20" (round-minutes "23"))))
  (testing "39 minutes rounds to 35"
    (is (= "35" (round-minutes "39"))))
  (testing "minutes divisible by 10 remain unchanged"
    (is (= "10" (round-minutes "10"))))
  (testing "minutes divisible by 5 remain unchanged"
    (is (= "15" (round-minutes "15")))))

(deftest calculate-hour-number-for-minutes
  (is (= 3 (hour-equivilant "15")))
  (is (= 6 (hour-equivilant "30"))))

(deftest generate-time-array-from-hour-minute-indexes
  (is (= [\o \o \h \o \o \m \o \o \o \o \o \o] (gen-time 2 5)))
  (is (= [\h \o \o \o \o \o \o \o \o \o \o \m] (gen-time 0 11)))
  (testing "both minute and hour index are the same should use x"
    (is (= [\o \o \o \o \o \o \x \o \o \o \o \o] (gen-time 6 6)))))

(deftest time-to-index-array-for-printing
  (is (= [\o \o \h \o \o \m \o \o \o \o \o \o] (gen-clock "02:25")))
  (is (= [\h \o \o \o \o \o \o \o \o \o \o \m] (gen-clock "12:55")))
  (testing "both minute and hour index are the same should use x"
    (is (= [\o \o \o \o \o \o \x \o \o \o \o \o] (gen-clock "06:30")))))
