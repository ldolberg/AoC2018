(defn insert-marble [i marbles m]
    (let [size (count marbles)]
    (concat (take (+ (mod (+ i 1) size) 1) marbles)  (list m) (drop (+ (mod (+ i 2) size) 1) marbles))))

(def init-marbles (insert-marble 1 (list 0 4 2 1 3) 5 ))

(println (loop [mbs (range 6 8) col init-marbles i 3]
    (println (mod (+ i 2) (inc (count col))))
    (if (empty? mbs)
        col
        (recur (rest mbs) (insert-marble i col (first mbs)) (mod (+ i 2) (inc (count col)))))))