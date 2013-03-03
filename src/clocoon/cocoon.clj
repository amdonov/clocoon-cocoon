(ns clocoon.cocoon
  (:require [clocoon.serialize :as serialize])
  (:import (clocoon.serialize Serializer)
           (org.springframework.core.io ClassPathResource)
           (org.springframework.beans.factory.xml XmlBeanFactory)
           (org.springframework.web.context.support
             WebApplicationContextUtils)))

(defn serializer [request name]
  (let [ctx (WebApplicationContextUtils/getWebApplicationContext
              (:servlet-context request))
        ser 
        (.getBean ctx (str "org.apache.cocoon.serialization.Serializer/" name))]
    (Serializer.
      (fn [os]
        (.setOutputStream ser os)
        ser)
      (.getMimeType ser) name)))

