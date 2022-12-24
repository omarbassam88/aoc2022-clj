(ns aoc2022.day13-test
  (:require [aoc2022.day13 :refer [parse-input part-1 part-2]]
            [clojure.test :as t :refer [deftest testing is]]
            [clojure.java.io :as io]))

(def input (slurp (io/resource "day13.test.txt")))

(deftest day13-part1
  (testing "Day 13 Part 1"
    (is (= (part-1 (parse-input input)) 13))))

(deftest day13-part2
  (testing "Day 13 Part 1"
    (is (= (part-2 (parse-input input)) 140))))
