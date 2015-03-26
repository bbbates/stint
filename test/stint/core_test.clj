(ns stint.core-test
  (:require [expectations :refer :all]
            [stint.core :refer :all]))

(expect "should return identity"
        (str-intern "should return identity"))

(expect "should return identity"
        (let [foo "return"]
          (str-intern "should ${foo} identity")))

(expect "should return identity"
        (let [foo (apply str [\r \e \t \u \r \n])]
          (str-intern "should ${foo} identity")))

(expect "should return identity"
        (let [foo "return"
              bar "should"
              gah "identity"
              goo " "]
          (str-intern "${bar}${goo}${foo}${goo}${gah}")))

(def some-value "namespaces")
(expect "with namespaces"
        (str-intern "with ${stint.core-test/some-value}"))

(expect "should allow spaces in the ${}"
        (let [foo "allow"
              bar "in "]
          (str-intern "should ${ foo } spaces ${bar }the ${}")))

