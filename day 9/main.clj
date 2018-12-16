;(nth col (mod (- i 7) size)
;(first (drop (mod (- i 7) size) col ))
(defn drop-nth [n coll]
    (vec (keep-indexed #(if (not= %1 n) %2) coll)))
    
 (defn insert-marble [i marbles m]
     (let [size (count marbles)]
     (concat (take  (if (= (+ i 2) size) size (mod (+ i 2) size)) marbles)  (list m) (drop (if (= (+ i 2) size) size (mod (+ i 2) size) )  marbles))))
 
 (def init-marbles (insert-marble 1 (list 0 4 2 1 3) 5 ))
 (def players 458)
 
 (println (sort-by val (loop [mbs (range 6 7201900) col init-marbles i 3 n 5 scores {}]
     ;; (println (inc (mod n players)) col scores)
     (if (= 0 (mod n 10000)) (println (Math/log (- 7201900 n))))
     (if (empty? mbs)
       scores
       (let [[m & r ] mbs
           size  (count col)
           j (mod (+ i 2) (inc size))
           player (inc (mod n players))]
           (if (= (mod m 23) 0)
             (recur (rest mbs) (drop-nth (mod (- i 7) size) col) (mod (- i 7 ) size) 
                    (inc n) (merge-with + scores {(keyword (str player)) (+ m (first (drop (mod (- i 7) size) col )))}))
             (recur (rest mbs) (insert-marble i col m) (if (= j 0) 1 j) (inc n) scores)))))))