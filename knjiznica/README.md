# Java EE 8 Demo

Delujoč DEMO Maven projekt (spletna aplikacija) z s funkcionalnosmi za člane, knjige, knjigomate, iskanje in izposojo knjig (JPA  + EJB + JSF + JAX-RS)

V Wildfly je potrebno dodati še temo in vrsto:

```
<jms-queue name="testQueue" entries="jms/queue/test java:jboss/exported/jms/queue/test"/>

<jms-topic name="testTopic" entries="jms/topic/test java:jboss/exported/jms/topic/test"/>
```

