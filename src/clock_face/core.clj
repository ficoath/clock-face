(ns clock-face.core
  (:gen-class))

(defn valid? [input]
  (re-matches #"([01]?[0-9]|2[0-3]):[0-5][0-9]" input))

(defn extract-hours [input-time]
  (read-string (get (re-matches #"(\d+):.*" input-time) 1)))

(defn convert-to-twelve-hour-time [hours]
  (if (> hours 11)
    (- hours 12)
    hours))

(defn extract-minutes [input-time]
  (get (re-matches #".*:(\d\d)" input-time) 1))

(defn round-minutes [input-time]
  (let [tens-minutes (get (re-matches #"(\d)\d" input-time) 1)
        ones-minutes (get (re-matches #"\d(\d)" input-time) 1)]
    (case ones-minutes
      ("1" "2" "3" "4") (str tens-minutes 0)
      ("6" "7" "8" "9") (str tens-minutes 5)
      input-time)))

(defn hour-equivilant [minutes]
  (/ (read-string minutes) 5))

(defn gen-time [hour minute]
  (let [clock-values [\o \o \o \o \o \o \o \o \o \o \o \o]]
    (if (= hour minute)
      (assoc clock-values hour \x)
      (assoc (assoc clock-values hour \h) minute \m))))

(defn gen-clock [time-str]
  (let [hours (convert-to-twelve-hour-time (extract-hours time-str))
        minutes (hour-equivilant (round-minutes (extract-minutes time-str)))]
    (gen-time hours minutes)))

; I'm sure there is a much better way to do this - suggestions?
(defn print-clock [clock]
  (println (str "      " (get clock 0)))
  (println (str "    " (get clock 11) "   " (get clock 1)))
  (println (str "  " (get clock 10) "       " (get clock 2)))
  (println (str (get clock 9) "   ｡◕‿◕｡   " (get clock 3)))
  (println (str "  " (get clock 8) "       " (get clock 4)))
  (println (str "    " (get clock 7) "   " (get clock 5)))
  (println (str "      " (get clock 6))))

(defn -main [& args]
  (if-let [input (valid? (read-line))]
    (print-clock (gen-clock (first input)))
    (println "invalid time, please use HH:MM format")))
