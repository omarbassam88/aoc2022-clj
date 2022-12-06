(ns aoc2022.day06-test
  (:require [aoc2022.day06 :refer [part-1 part-2]]
            [clojure.test :as t :refer [deftest testing is]]))


(deftest day06-part1
  (testing "Day 6 Part 1"
    (is (= (part-1 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 7))
    (is (= (part-1 "bvwbjplbgvbhsrlpgdmjqwftvncz") 5))
    (is (= (part-1 "nppdvjthqldpwncqszvftbrmjlhg") 6))
    (is (= (part-1 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 10))
    (is (= (part-1 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 11))))

(deftest day06-part2
  (testing "Day 6 Part 2"
    (is (= (part-2 "mjqjpqmgbljsphdztnvjfqwrcgsmlb") 19))
    (is (= (part-2 "bvwbjplbgvbhsrlpgdmjqwftvncz") 23))
    (is (= (part-2 "nppdvjthqldpwncqszvftbrmjlhg") 23))
    (is (= (part-2 "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg") 29))
    (is (= (part-2 "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw") 26))))
