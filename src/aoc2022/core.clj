(ns aoc2022.core)


(defn -main [day-str]
  (let [day (parse-long day-str)]
    (println "AOC 2022 Day " day)
    ((requiring-resolve (symbol (format "aoc2022.day%02d/-main" day))))))
