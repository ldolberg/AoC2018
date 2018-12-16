

(defn rotate-right [deque]
    (into  [(first deque)] (rest deque))
)


(defn rotate-left [deque]
    (conj (drop-last deque) (peek deque)) 
)

(defn drop-nth [n coll]
    (vec (keep-indexed #(if (not= %1 n) %2) coll)))
    
 (defn insert-marble [i marbles m]
     (let [size (count marbles)]
     (reduce into (list (subvec marbles 0 (if (= (+ i 2) size) size (mod (+ i 2) size)) )  [m] 
            (subvec marbles (if (= (+ i 2) size) size (mod (+ i 2) size) ) ) ))))
 
 (def init-marbles (insert-marble 1 [ 0 4 2 1 3] 5 ))
 (def players 9)
 
 (println (sort-by val (loop [mbs (range 6 29) col init-marbles i 3 n 5 scores {}]
    ;  (println (inc (mod n players)) col scores)
     (if (empty? mbs)
       scores
       (let [[m & r ] mbs
           size  (count col)
           j (mod (+ i 2) (inc size))
           player (inc (mod n players))]
           (if (= 0 (mod n 10000)) (println (* (/ n 7201900) 1.0)))
           (if (= (mod m 23) 0)
             (recur (rest mbs) (drop-nth (mod (- i 7) size) col) (mod (- i 7 ) size) 
                    (inc n) (merge-with + scores {(keyword (str player)) (+ m (first (drop (mod (- i 7) size) col ))
                    )}))
             (recur (rest mbs) (insert-marble i col m) (if (= j 0) 1 j) (inc n) scores)))))))