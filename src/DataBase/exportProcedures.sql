--------------------------------------------------------
--  File created - Monday-February-11-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Procedure GET_SYS_GENKEY
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "GET_SYS_GENKEY" 
(
  retVal out varchar2,  --返回生成序号
  intype in varchar2,  --序号类型
  inCount in varchar2  --序列号补0位数
)
 as
  nowDateStr varchar2(50);--当前年月日
  nowmaxcount number(20); --当天最大序号
  IS_EXIST NUMBER;  --数据是否存在
  nowprefix varchar2(50);
 begin
      --取到当前年月日
      select to_char(sysdate,'yyyymmdd') into nowDateStr from dual;
      --取到当天最大key
      select max(t.maxcount) into nowmaxcount from basis.basis_tb_SYS_GENKEY t where t.datestr = nowDateStr and t.type = intype;
      
      --取到最大值的前缀
      select distinct(t.prefix) into nowprefix from basis.basis_tb_SYS_GENKEY t where   t.type = intype and t.prefix is not null;
      
      
      --每天加1
      --为空则设置为0
      if nowmaxcount is null then
         nowmaxcount := 0;
      end if;
      nowmaxcount := nowmaxcount + 1;

      --更新临时表
      SELECT COUNT(1) INTO IS_EXIST FROM basis.basis_tb_SYS_GENKEY t where t.datestr = nowDateStr and t.type = intype;
      IF (IS_EXIST != 0) THEN
         update basis.basis_tb_SYS_GENKEY set MAXCOUNT = nowmaxcount,prefix=nowprefix where datestr = nowDateStr and type = intype;
      else
         insert into basis.basis_tb_SYS_GENKEY (DATESTR,MAXCOUNT,TYPE,prefix)values(nowDateStr,nowmaxcount,intype,nowprefix);
      end if;
     --补0
      select nowprefix||nowDateStr||LPAD (nowmaxcount , inCount , '0') key into retVal from dual;
 end;
--------------------------------------------------------
--  DDL for Procedure PRC_ADDID
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "PRC_ADDID" IS


  vs_code varchar2(50);
  vs_message varchar2(2000);
  is_exit number;
  idd number;

BEGIN


  select count(*) into is_exit  from  basis.basis_tb_disti_branch;



    if(is_exit>0) then

    declare
      Cursor cursor is
       select branch_code from  basis.basis_tb_disti_branch;
    begin

    for curs in cursor LOOP
      begin
      select basis.basis_seq_disti_branch.NEXTVAL into idd from dual;
            update  basis.basis_tb_disti_branch set id= idd
			where branch_code=curs.branch_code;

      end;
    end LOOP;

    end;

  end if;


  COMMIT;

EXCEPTION

  WHEN OTHERS THEN

    ROLLBACK;

    RETURN;
END;
--------------------------------------------------------
--  DDL for Procedure PRC_EDIQUOTEMESSAGE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "PRC_EDIQUOTEMESSAGE" IS
  is_exit number;

BEGIN


  select count(*) into is_exit  from  basis.basis_tb_quoteinfo
         where data_from='2' and isMessage is null
         and (
         ((endCustomer_name is not null and endCustomer_id is not null) or (endCustomer_name is null and endCustomer_id is null))
         and (purchaseCustomer_name is not null and purchaseCustomer_id is not null)
         );


    if(is_exit>0) then

    declare
      Cursor cursor is
         select id from  basis.basis_tb_quoteInfo
         where data_from='2' and isMessage is null
         and (
         ((endCustomer_name is not null and endCustomer_id is not null) or (endCustomer_name is null and endCustomer_id is null))
         and (purchaseCustomer_name is not null and purchaseCustomer_id is not null)
         );
    begin

    for curs in cursor LOOP
      begin
           insert into basis_tb_message
           (id,sendnumber,content,type,createtime,state)
           (
               SELECT basis.basis_sq_message.nextval,
                   mm.emp_email,                 
                   'Hi '||mm.emp_name||chr(60)||'br'||chr(62)||chr(38)||'nbsp;'||chr(38)||'nbsp;Disti '||aa.cusgroup_id|| ' submit a Quote,coded as '||aa.quote_id|| ', please login platform for approval' ||chr(60)||'br'||chr(62),
                   'EDI Quote',
                   sysdate,
                   0
                from  basis.basis_tb_quoteInfo aa left outer join basis.basis_tb_salesemp_info mm on aa.create_userId = mm.emp_id    where aa.id = curs.id
            );
            update  basis.basis_tb_quoteInfo set isMessage='Y' where id=curs.id;

      end;
    end LOOP;

    end;

  end if;


  COMMIT;

EXCEPTION

  WHEN OTHERS THEN

    ROLLBACK;

    RETURN;
END;
--------------------------------------------------------
--  DDL for Procedure PRC_GETSYSGENKEY
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "PRC_GETSYSGENKEY" (incount     varchar2,
                                                  intype        VARCHAR2,
                                                  RESULTCODE OUT VARCHAR2) IS

  nowDateStr varchar2(50);--当前年月日
  nowmaxcount number(20); --当天最大序号
  IS_EXIST NUMBER;  --数据是否存在
  nowprefix varchar2(50);
  nowtyepname varchar2(50);
  vs_code varchar2(50);
BEGIN



   --取到当前年月日
      select to_char(sysdate,'yyyymmdd') into nowDateStr from dual;
      --取到当天最大key
      select max(t.maxcount) into nowmaxcount from basis.basis_tb_SYS_GENKEY t where t.datestr = nowDateStr and t.type = intype;
      
      --取到最大值的前缀
      select distinct(t.prefix),typeName into nowprefix,nowtyepname from basis.basis_tb_SYS_GENKEY t where   t.type = intype and t.prefix is not null;
      
      
      --每天加1
      --为空则设置为0
      if nowmaxcount is null then
         nowmaxcount := 0;
      end if;
      nowmaxcount := nowmaxcount + 1;

      --更新临时表
      SELECT COUNT(1) INTO IS_EXIST FROM basis.basis_tb_SYS_GENKEY t where t.datestr = nowDateStr and t.type = intype;
      IF (IS_EXIST != 0) THEN
         update basis.basis_tb_SYS_GENKEY set MAXCOUNT = nowmaxcount,prefix=nowprefix,typeName=nowtyepname where datestr = nowDateStr and type = intype;
      else
         insert into basis.basis_tb_SYS_GENKEY (DATESTR,MAXCOUNT,TYPE,prefix,typename)values(nowDateStr,nowmaxcount,intype,nowprefix,nowtyepname);
      end if;
     --补0
      select nowprefix||nowDateStr||LPAD (nowmaxcount , incount , '0') key into vs_code from dual;       
      RESULTCODE := vs_code;  
  
  
  
  COMMIT;

EXCEPTION

  WHEN OTHERS THEN
   
    vs_code       := '1';
    RESULTCODE       := vs_code;  
    ROLLBACK;

    RETURN;
END;
--------------------------------------------------------
--  DDL for Procedure PRC_POSDR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "PRC_POSDR" IS
  is_exit number;
 is_exit1 number;
BEGIN

  select count(*) into is_exit  from  basis.basis_vw_pos_dr where sumResale>1000;

    if(is_exit>0) then

    declare
      Cursor cursor is
       select sumResale,material_name,drNum from  basis.basis_vw_pos_dr where sumResale>1000;
    begin

    for curs in cursor LOOP
      begin
            select count(1)  into is_exit1 
             from  basis.basis_tb_design_reg_detail dd
                ,  basis.basis_tb_design_reg m
             where dd.main_id = m.id
               and SUBSTR(m.CUSTOMER_ID,0,1) = 7
               and dd.drNum = curs.drNum
               and dd.material_name = curs.material_name
               and dd.state=1
               and dd.project_state!=2
               and m.CREATE_USERID in (select EMP_CODE from BASIS_TB_USER_ROLE where ROLE_ID not in ('DR')) ;
           
           if(is_exit1>0) then
                update basis.basis_tb_design_reg_detail
                   set project_state=2
                     , product_date=sysdate
                     , end_date=add_months(sysdate,24)
                     , design_win=sysdate
                     , SALER_DESIGN_STATUS= 4
                     , latest_time = sysdate
                 where drNum = curs.drNum
                   and material_name = curs.material_name
                   and state = 1
                   and project_state!=2;

                update basis.basis_tb_design_reg
                   set end_date = add_months(sysdate,24)
                     , latest_time = sysdate
                 where drNum = curs.drNum;
                --如果是disti 更新状态为DW 状态为Final Approved
                --     and main_id in (SELECT id FROM BASIS.basis_tb_design_reg m , BASIS.BASIS_TB_SALESEMP_INFO u                    where m.id = a.MAIN_ID and m.CREATE_USERID = u.EMP_ID and u.ORG_ID is null ) 
               --  update  basis.basis_tb_design_reg_detail a
              --       set SALER_DESIGN_STATUS = 4
              -- where drNum=curs.drNum                 
              --      and material_name = curs.material_name and state=1 and  project_state!=2;
            --disti 发邮件了, DW regional sales head (HK10_H_DW_Submission)，sales head，DW marketing
              
                    insert into BASIS_TB_MESSAGE (id,SENDNUMBER,CONTENT,STATE ,CREATETIME,TYPE)
                    select BASIS_SQ_MESSAGE.nextval,
                            emp_code sendnumber,
                           'Disti '||(SELECT DISTI_BRANCH FROM BASIS.basis_tb_design_reg where drnum = curs.drNum)||'’s '||curs.drNum||' has turned to design win, please check in Portal.',0,sysdate,'DR Design Win' 
                      from BASIS.BASIS_TB_SALESEMP_INFO
                     where (
                     emp_id in (select EMP_CODE from BASIS_TB_USER_ROLE where ROLE_ID in ('HK10_H_DW_MktAppr','HK10_H_DW_SalesAppr')) or 
                     emp_id in (select aa.EMP_CODE from BASIS_TB_USER_ROLE aa ,BASIS.BASIS_TB_SALESEMP_INFO bb , 
                                    BASIS.BASIS_TB_SALECOUNTRY ct , BASIS.basis_tb_design_reg re , BASIS_TB_END_CUSTOMEINFO cu 
                                    where AA.ROLE_ID = 'HK10_H_DW_Submission' and    AA.EMP_CODE = BB.emp_id  and 
                                         ct.userid = BB.emp_id and ct.COUNTRY_CODE =  cu.COUNTRY  and  re.drnum = curs.drNum and
                                          re.ENDCUS_ID = cu.END_CUSTOMER_ID));
                     
                
                end if;
      end;
    end LOOP;

    end;

  COMMIT;
  end if;




EXCEPTION

  WHEN OTHERS THEN

    ROLLBACK;

    RETURN;
END;
--------------------------------------------------------
--  DDL for Procedure PRC_TOEXPIRYDR
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "PRC_TOEXPIRYDR" IS
  is_exit number;
  is_dr_header_exit number;
  is_dw_reject_exit number;
  
BEGIN

  select count(*) into is_exit  from  basis.basis_tb_design_reg_detail where state!=3 and sysdate >= end_date;

  if(is_exit > 0) then
      -- 遍历更改状态为失效
    declare
      Cursor cursor is
      select id
        from basis.basis_tb_design_reg_detail
       where state != 3
         and sysdate >= end_date;
    begin

      for curs in cursor LOOP
        begin
          update  basis.basis_tb_design_reg_detail
             set state=3
               , latest_time = sysdate
           where id= curs.id;
        end;
      end LOOP;
    end;
  end if;
  
  select count(*) into is_dr_header_exit  from  basis.basis_tb_design_reg where state!=3 and sysdate >= end_date;

  if(is_dr_header_exit > 0) then
      -- 遍历更改状态为失效
    declare
      Cursor cursor is
      select id
        from basis.basis_tb_design_reg
       where state != 3
         and sysdate >= end_date;
    
    begin

      for curs2 in cursor LOOP
        begin
          update  basis.basis_tb_design_reg
             set state=3
               , latest_time = sysdate
             where id= curs2.id;
        end;
      end LOOP;
    end;
  end if;

  select count(*) into is_dw_reject_exit from  basis.basis_tb_design_reg_detail
   where state = 3
     and project_state = 2
     and saler_design_status in (3, 5, 7, 9);

  if(is_dw_reject_exit > 0) then
      -- 遍历更改状态为失效
    declare
      Cursor cursor is
      select id
        from basis.basis_tb_design_reg_detail
       where state = 3
         and project_state = 2
         and saler_design_status in (3, 5, 7, 9);
    
    begin

      for curs3 in cursor LOOP
        begin
          update  basis.basis_tb_design_reg_detail
             set project_state=1
               , latest_time = sysdate
           where id= curs3.id;
        end;
      end LOOP;
    end;
  end if;


  COMMIT;

EXCEPTION

  WHEN OTHERS THEN

    ROLLBACK;

    RETURN;
END;
--------------------------------------------------------
--  DDL for Procedure PRC_TOEXPIRYQUOTE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "PRC_TOEXPIRYQUOTE" IS


  vs_code varchar2(50);
  vs_message varchar2(2000);
  is_exit number;

BEGIN


  select count(*) into is_exit  from  basis.basis_tb_quote_detail
         where (state in (3,4,5) and isAgree=0 and sysdate >= (latest_time+60)) and id > 13287
         --(isAgree=1 and sysdate >= add_months(latest_time,6))
         ;


    if(is_exit>0) then

    declare
      Cursor cursor is
       select id,main_id,state from  basis.basis_tb_quote_detail
              where (state in (3,4,5) and isAgree=0 and sysdate >= (latest_time+60)) and id > 13287
              --or (isAgree=1 and sysdate >= add_months(latest_time,6))
              ;
    begin

    for curs in cursor LOOP
      begin
            update  basis.basis_tb_quote_detail set isAgree=2
			where id=curs.id;

      end;
    end LOOP;

    end;

  end if;


  COMMIT;

EXCEPTION

  WHEN OTHERS THEN

    ROLLBACK;

    RETURN;
END;
--------------------------------------------------------
--  DDL for Procedure PRC_UPDATEDRSTATE
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "PRC_UPDATEDRSTATE" IS

  dcount number;--行项目总数
  pendingCount number;--待处理个数
  approveCount number;--通过个数
  rejectCount number;--拒绝个数
  winCount number;--design_win数
  is_exit number;--quote数量，>0则执行状态实时更新操作

BEGIN

  select count(*) into is_exit  from  basis.basis_tb_design_reg;



  if is_exit>0 then

        declare
          Cursor cursor is
           select drNum,state from basis.basis_tb_design_reg;
    
        begin

        for curs in cursor LOOP
            begin
                 select count(*) into dcount        from  basis.basis_tb_design_reg_detail where drNum=curs.drNum; --行项目总数
                 select count(*) into pendingCount from  basis.basis_tb_design_reg_detail where drNum=curs.drNum and state =0;--待处理个数
                 select count(*) into approveCount from  basis.basis_tb_design_reg_detail where drNum=curs.drNum and state =1;--通过个数
                 select count(*) into rejectCount  from  basis.basis_tb_design_reg_detail where drNum=curs.drNum and state =2; --拒绝个数
                 select count(*) into winCount  from  basis.basis_tb_design_reg_detail where drNum=curs.drNum and project_state =2; --拒绝个数
      
                 if dcount>0 and approveCount>0 and curs.state!=1 then
                    update basis.basis_tb_design_reg set state = 1 where drNum = curs.drNum;--有一个Approve则表头状态为Approve
      
                 elsif dcount>0 and rejectCount = dcount and curs.state!=2 then
                    update  basis.basis_tb_design_reg set state = 2 where drNum = curs.drNum;--所有行项目reject则表头reject
      
                 elsif dcount>0 and pendingCount = dcount and curs.state!=0 then
                    update  basis.basis_tb_design_reg set state = 0 where drNum = curs.drNum;--所有行项目pengding则表头pending
                 end if;
                 
                 if dcount>0 and winCount>0 then 
                   update  basis.basis_tb_design_reg set project_state = 2 where drNum = curs.drNum;--有一行项目design_win则表头design_win
                 end if;
                 
            end;
       end LOOP;

    end;

 end if;


  COMMIT;

EXCEPTION

  WHEN OTHERS THEN

    ROLLBACK;

    RETURN;
END;
--------------------------------------------------------
--  DDL for Procedure SEQ_RESET
--------------------------------------------------------
set define off;

  CREATE OR REPLACE PROCEDURE "SEQ_RESET" (v_seqname varchar2) as n number(10);
tsql varchar2(100);
 begin
 execute immediate 'select '||v_seqname||'.nextval from dual' into n;
  n:=-(n-1);
  tsql:='alter sequence '||v_seqname||' increment by '|| n;
  execute immediate tsql;
 execute immediate 'select '||v_seqname||'.nextval from dual' into n;
  tsql:='alter sequence '||v_seqname||' increment by 1';
 execute immediate tsql;
 end seq_reset;
