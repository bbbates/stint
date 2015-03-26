(ns stint.core)

(def $-intern-pattern #"\$\{([ A-Za-z0-9+*!-_?/]+)\}")

(declare str->binding)

(defmacro str-intern
  [$]
  (let [bindings (zipmap (map name (keys &env)) (keys &env))]
    `(let [matches# (re-seq $-intern-pattern ~$)
           matches-vals# (map (comp (partial str->binding ~bindings) clojure.string/trim last) matches#)
           filter-str# (reduce (fn [st# m#] (clojure.string/replace st# (first m#) "%s")) ~$ matches#)]
       (if (seq matches#)
         (apply format filter-str# matches-vals#)
         ~$))))

(defn str->binding
  [str->vals v]
  (let [bound-v (or (get str->vals v) (resolve (symbol v)))]
    (assert bound-v (str-intern "Cannot find interned value with symbol '${v}'"))
    (if (var? bound-v) @bound-v bound-v)))

(defn s [form]
  `(str-intern ~form))
