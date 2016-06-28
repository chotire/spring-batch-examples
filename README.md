# Maven Build 후 실행
  - java -jar spring-batch-examples-1.0.jar spring/batch/jobs/job-trade.xml tradeJob startDate=2016-3-14 endDate=2016-3-16
  - Usage : 
        java -jar spring-batch-examples-1.0.jar jobPath <options> jobIdentifier (jobParameters)
  - Linux에서 실행 할 경우 프로세스가 구동되므로 프로세스를 실행한 콘솔 창을 닫을 경우 Spring Batch가 같이 종료가 된다.
    그렇기 때문에 반드시 Background mode로 실행시켜야 한다.
    java -jar spring-batch-examples-1.0.jar spring/batch/jobs/job-trade.xml tradeJob startDate=2016-3-14 endDate=2016-3-16 &
    
============================================================================================
DELETE FROM BATCH_STEP_EXECUTION; 
DELETE FROM BATCH_STEP_EXECUTION_CONTEXT; 
DELETE FROM BATCH_JOB_EXECUTION; 
DELETE FROM BATCH_JOB_EXECUTION_CONTEXT; 
DELETE FROM BATCH_JOB_INSTANCE; 
DELETE FROM BATCH_JOB_PARAMS; 