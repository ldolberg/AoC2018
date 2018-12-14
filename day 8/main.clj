(def input (map #(. Integer parseInt %) (clojure.string/split (slurp "day 8/input") #" ")))
; (println input)
(defn inctop [act]
    ; (println "e" act)
    [(first act) (second act) (inc (nth act 2))])

(println (loop [ chain (drop 2 input) res (list) stack (list  [ (first input) (second input) 0 ] ) rc (list)]  
    ; (println (cou)nt chain) chain stack res  ) ;(first chain) (inctop (first stack)) (rest stack) (drop (second chain) (rest (rest chain))))
    (if (not (empty? chain) )
    (let [ [c m & r] chain
           [act & rstack ]  stack 
           incact (inctop act)
           exec_p  (if (= c 0) ; terminal node ; take payload
                        (list (drop m r)
                        (conj res (reduce + (take m r)))
                        (cons incact rstack) rc)
            (if (and (= (first act) (nth act 2)) (empty? chain)) ; finished the list
                        (list (list ) (concat res chain) nil rc)
                        (if (= (nth act 2) (first act)) ; all the child done for this node ; take the payload 
                            (list (drop (second act) chain)
                                (concat res (if (> (count (filter #(and (< %1 (first act)) (> %1 0)) (take (second act) chain))) 0) 
                                                   (take (second act) chain)
                                                    (map #(* 0 %) (range (second act)))))
                                (cons (first rstack) (rest rstack))
                                (concat rc (if (> (count (filter #(and (< %1 (first act)) (> %1 0)) (take (second act) chain))) 0)
                                            (map #( nth res  %) (filter #(and (< %1 (first act)) (> %1 0)) (take (second act) chain)))
                                            (list 0)))); 
                            (list r res (cons [ c m 0 ] (cons incact rstack)) rc) ; a new node starts
                        )))]
                        ; (println rc)
            ; (if (= (nth act 2) (first act)) (println stack (if (> (count (filter #(and (< %1 (first act)) (> %1 0)) (take (second act) chain))) 0)
            ; (reduce + (map #( nth res  %) (filter #(and (< %1 (first act)) (> %1 0)) (take (second act) chain))))
            ; 0)))
            (recur (first exec_p) (second exec_p) (nth exec_p 2) (nth exec_p 3))
            )
            (list (reduce + res) (reduce + rc)))))

;; ; (println  input [] [ (first input) (second input) 0 ])
;; (println (parse (drop 2 input) (list ) (list  [ (first input) (second input) 0 ] )))