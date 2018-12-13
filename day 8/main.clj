(def input (map #(. Integer parseInt %) (clojure.string/split (slurp "day 8/input") #" ")))
; (println input)
(defn inctop [act]
    ; (println "e" act)
    [(first act) (second act) (inc (nth act 2))])

(defn parse [ chain res stack]
    (println (count chain) (+ (reduce + res) (reduce + chain)))
    ;  (println (count chain) chain stack res)
    (if (not (empty? chain) )
    (let [ [c m & r] chain
           [act & rstack ]  stack 
           incact (if (empty? stack) (inctop [c m 0]) (inctop act))] 
            ; (println (count chain) (second act))
            ; (if (= c 0) (println "( c m [] [" (take m r) "]"))
            ; (if (not (empty? stack))
            ;     (if (= (nth act 2) (first act)) (println "reduce" chain (concat res (take (second act) chain)) "."  stack "." act "." (rest rstack) ) ))
            ; (if (or (empty? stack)
            ; (if (and (not (empty? stack)) (= (nth act 2) (first act)) (println "reduce" (take (second act) chain )))) 
            (if (and (= (nth act 2) (count chain))) (println "fin" (reduce + res) (reduce + chain))
            (if (= c 0)
                 (parse (drop m r)
                        (concat res (take m r))
                        (cons incact rstack))
                (if (and (= 0 1) (empty? stack))
                    (parse r res (cons [c m 0] stack))
                    (if (and (= (first act) (nth act 2)) (empty? chain))
                        (concat res input)
                        (if (= (nth act 2) (first act))
                            (parse (drop (second act) chain)
                                (concat res (take (second act) chain))
                                (cons (inctop (first rstack)) (rest rstack)))
                            (parse r res (cons [ c m 0 ] (cons incact rstack)))
                        )))
            ))

            (concat res nil))))

; (println  input [] [ (first input) (second input) 0 ])
(println (parse (drop 2 input) (list ) (list  [ (first input) (second input) 0 ] )))