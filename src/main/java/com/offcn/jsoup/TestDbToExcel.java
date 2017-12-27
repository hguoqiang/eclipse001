/*package com.offcn.jsoup;

public class TestDbToExcel {
15 
16     public static void main(String[] args) {
17         try {
18             WritableWorkbook wwb = null;
19              
20                // 创建可写入的Excel工作簿
21                String fileName = "D://book.xlsx";
22                File file=new File(fileName);
23                if (!file.exists()) {
24                    file.createNewFile();
25                }
26                //以fileName为文件名来创建一个Workbook
27                wwb = Workbook.createWorkbook(file);
28 
29                // 创建工作表
30                WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
31                
32                //查询数据库中所有的数据
33                List<StuEntity> list= StuService.getAllByDb();
34                //要插入到的Excel表格的行号，默认从0开始
35                Label labelId= new Label(0, 0, "编号(id)");//表示第
36                Label labelName= new Label(1, 0, "姓名(name)");
37                Label labelSex= new Label(2, 0, "性别(sex)");
38                Label labelNum= new Label(3, 0, "薪水(num)");
39                
40                ws.addCell(labelId);
41                ws.addCell(labelName);
42                ws.addCell(labelSex);
43                ws.addCell(labelNum);
44                for (int i = 0; i < list.size(); i++) {
45                    
46                    Label labelId_i= new Label(0, i+1, list.get(i).getId()+"");
47                    Label labelName_i= new Label(1, i+1, list.get(i).getName());
48                    Label labelSex_i= new Label(2, i+1, list.get(i).getSex());
49                    Label labelNum_i= new Label(3, i+1, list.get(i).getNum()+"");
50                    ws.addCell(labelId_i);
51                    ws.addCell(labelName_i);
52                    ws.addCell(labelSex_i);
53                    ws.addCell(labelNum_i);
54                }
55              
56               //写进文档
57                wwb.write();
58               // 关闭Excel工作簿对象
59                wwb.close();
60              
61         } catch (Exception e) {
62             // TODO Auto-generated catch block
63             e.printStackTrace();
64         } 
65     }
66 }*/