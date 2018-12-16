(defn pl [cell]
    (let [pw (* (+ (* (+ 10 (first cell)) (second cell)) 8772) (+ 10 (first cell))) ]
        (- (int (/ (mod pw 1000) 100)) 5)))
(defn pgrid[n] (for [x (range 1 (- 301 n))
      y (range 1 (- 301 n))]
     (let [ subgrid (for [i (range 0 n) j (range 0 n)]
            [(+ x i) (+ y j)]
        )] 
        {:cell [x y] :pw (reduce + (map pl subgrid))})))
(pmap  (fn[n] (println n (apply max-key :pw (pgrid n)))) (range 1 300))