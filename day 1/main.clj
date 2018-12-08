(defn count-rep [l e]
  (count (filter #(= % e) l)))
          
(defn find-repeating-fq []
  (let [numbers (map #(java.lang.Integer/parseInt %) (clojure.string/split (clojure.string/replace (slurp "input") #"\+" "") #"\n"))]
    (loop [[head & tail] (flatten (repeat numbers )) solution :na ac 0 freq '()]
      (if (= solution :na)  
          (recur tail (if (= (count-rep freq ac) 0) :na ac) (+ ac (int head)) (cons ac freq) )
          ac))))
