(defn count-rep [l e]
  (count (filter #(= % e) l)))
            
  ;;(find-repeating-fq)
(defn is-in [l e]
  (loop [[h & t] l r false]
    (if (and (not (nil? h)) (= r false))
        r
        (recur t (= h e))
    )
  )
)

  (defn repeated-lettes [i]
  (let [numbers (clojure.string/split (slurp "input2") #"\n")]
      (reduce + (map #(if (> % 0) 1 0) (map (fn[l] (count (filter #(= i %) l))) (loop [[head & tail] numbers r '()]
      (if (nil? head)
          r
          (recur tail (cons (map (fn [x] (count-rep head x)) (set head)) r))
      )
    ))))))
    
(* (repeated-lettes 2) 
   (repeated-lettes 3))

(defn compare-ids [u v]
  (loop [[uh & ut] u [vh & vt] v dd 0 res ""]
    (if (nil? uh)
        (if (= dd 1) res "")
        (if (> dd 1)
          ""
          (recur ut vt (+ dd  (if (not= uh vh) 1 0))   (cons (if (= uh vh) uh "") res))))
  )
)

(defn cart [colls]
  (if (empty? colls)
    '(())
    (for [x (first colls)
          more (cart (rest colls))]
      (cons x more))))

;; (compare-ids "wkz3yfdp1uzeqvajtbbosngkxc" "wkz3yfdpluzeqvajtbbjsngkxc")

(println (let [numbers (clojure.string/split (slurp "input2") #"\n")]
  (map #(apply compare-ids %) (filter (fn [x] (not= (first x) (second x))) (cart (list numbers numbers))))))