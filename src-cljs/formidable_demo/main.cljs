(ns formidable-demo.main
  (:require [crate.core :as crate]
            [dommy.core :as d :refer-macros [sel sel1]]
            [formidable.dom :as fd]
            [formidable.core :as f]))

(def demo-form
  {:fields [{:name :h1 :type :heading :text "Section 1"}
            {:name :full-name}
            {:name "user[email]" :type :email}
            {:name :spam :type :checkbox :label "Yes, please spam me."}
            {:name :password :type :password}
            {:name :password-confirm :type :password}
            {:name :h2 :type :heading :text "Section 2"}
            {:name :note :type :html
             :html [:div.alert.alert-info "Please make note of this note."]}
            {:name :date :type :date-select}
            {:name :time :type :time-select}
            {:name :flavors :type :checkboxes
             :options ["Chocolate" "Vanilla" "Strawberry" "Mint"]}
            {:name :location :type :compound
             :fields [{:name :city :placeholder "City" :class "input-medium"}
                      {:name :state :type :us-state :placeholder "Select a state"}]}]
   :validations [[:required [:full-name "user[email]" :password]]
                 [:min-length 4 :password]
                 [:equal [:password :password-confirm]]
                 [:min-length 2 :flavors "select two or more flavors"]
                 [:complete :location]]})

(defn- get-rendered-from-url []
  (js* "location.search.substring(1).split('=')[1]"))

(def renderer-form
  {:method "get"
   ;:renderer :inline
   :submit-label nil
   :fields [{:name :renderer
             :type :select
             :options ["bootstrap3-stacked"
                       "bootstrap-horizontal"
                       "bootstrap-stacked"
                       ;"bootstrap3-horizontal"
                       "table"]
             :onchange "this.form.submit()"}]})

(defn render-demo-form []
  (let [now (js/Date.)
        defaults {:spam true
                  :date now
                  :time now}
        renderer (if (get-rendered-from-url)
                   (keyword (get-rendered-from-url))
                   :bootstrap3-stacked)]
    (f/render-form (assoc demo-form :renderer renderer :values defaults))))

(defn main []
  (when-let [container (sel1 "#cljs-container")]
    (d/append! container (crate/html (render-demo-form)))
    (fd/handle-submit
      demo-form container
      (fn [params]
        (js/alert (pr-str params))))))

(main)
