package exception;

public class Account {
    double balance = 0;

    public Account(double balance){
        this.balance = balance;
    }

    public double getBalance(){
        return balance;
    }

    public void deposit(double awt){
        this.balance += awt;
    }

    public void withdraw(double awt) throws OverdraftException{
        this.balance -= awt;
        if(this.balance < 0){
            throw new OverdraftException(" 已经透支，透支额：" ,balance);
        }
    }

    public static void main(String[] args) {
        Account account1 = new Account(1000);
        System.out.println(" 原始账户余额：" + account1.getBalance() + "元");
        System.out.println(" 存钱150元");
        account1.deposit(150);
        System.out.println(" 账户余额：" + account1.getBalance() + "元");
        System.out.println(" 取钱1200元");
        try {
            account1.withdraw(1200);
        } catch (OverdraftException e) {
            e.printStackTrace();
        }
        System.out.println(" 账户余额：" + account1.getBalance() + "元");
    }
}
