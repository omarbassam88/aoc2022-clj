(ns aoc2022.day09-test
  (:require [aoc2022.day09 :refer [parse-input part-1 part-2]]
            [clojure.test :as t :refer [deftest testing is]]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day09.test.txt")))

(deftest day09-part1
  (testing "Day 9 Part 1"
    (is (= (part-1 (parse-input input)) 21))))
