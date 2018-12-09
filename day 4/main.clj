(def fp "day 4/input")          
(load "bst")

(defn numbers-do [f]
    (let [numbers (clojure.string/split (slurp fp)  #"\n")] (f numbers)))


(defn load-shift [x] 
    (let [order (re-find #"\[(\d+)-(\d+)-(\d+) (\d+):(\d+)\] .*" x)]
    ; (println order)
        (if order
            (apply assoc {} (interleave [:y :m :d :h :M :p] (concat (rest order) (list (first order)))))
            nil
        ) 
    )
)

(defn index-shift [x]
    (let [{y :y m :m d :d h :h M :M p :p} x]
        [(bigint (clojure.string/join "" (list y m d h M))) p]  
    )
)

(def data (in-order (loop [elems (map index-shift (numbers-do (partial map load-shift))) bt nil]
            (let [[h & t] elems
                res bt]
                (if (nil? h)
                    res
                    (recur t (insert bt (first h) (second h)))
                ) 
            ) 
        )
    )
)

(def grouped_data (filter :guard (reverse (loop [[h & t] data
      res (list) acc (list)]
    (if (nil? h)
        (conj res acc)
        (if (clojure.string/includes? (:val h) "Guard")
            (recur t (conj res acc) {:guard (second (re-find #"\#(\d+)" (:val h) )) :seq (list (:val h))})
            (recur t res (assoc acc :seq (conj (:seq acc) (:val h))))
        ))
      )     
    )
  )
)

(defn reduce_shifts_per_guard [x]
    (loop [[h & t] x 
            res {}]
       (if h
            (recur t (assoc res (keyword (:guard h)) (cons (:seq h)  ((keyword (:guard h)) res))))
            res
        )
    )
)

(defn process [x] 
    (let [[k v] x
         values (partition 2 (map #(java.lang.Integer/valueOf (second (re-find #":(\d+)" %))) (filter #(not (clojure.string/includes? % "Guard")) (flatten v))))]
        [k (count values) values]
        (loop [[h & t] values
               total_sleep 0 acc {}]
            (if h
                (recur t (+ total_sleep (- (first h) (second h))) (merge-with + (frequencies (range (second h) (first h))) acc))
                (if (= total_sleep 0) [k total_sleep acc ] [k total_sleep (apply max-key val acc)])
            )
        )
    )
)

(println (apply max-key #(second (second (rest %))) (filter #(> (second %)  0) (map process (reduce_shifts_per_guard grouped_data)))))

(println (apply max-key second (filter #(> (second %)  0) (map process (reduce_shifts_per_guard grouped_data)))))
