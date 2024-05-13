package ru.job4j.bank;

import java.util.*;

/**
 *Банковский сервис.
 * 1. Регистрировать пользователя.
 * 2. Удалять пользователя из системы.
 * 3. Добавлять пользователю банковский счет. У пользователя системы могут быть несколько счетов.
 * 4. Переводить деньги с одного банковского счета на другой счет.
 */
public class BankService {
    /**
     * Сдесь хранятся все пользователи и принадлежащие и списки их аккаунтов в коллекции типа HashMap.
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод принимает на вход пользвателя банка и добавляет его в карту.
     * По умолчанию создаётся пустой список аккаунтов для этого пользователя.
     * @param user Пользователь банка.
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод принимает на вход номер паспорта и удаляет из карты пользователя с этим паспортом
     * и список аккаунтов этого пользователя.
     * @param passport Номер паспорта пользователя банка.
     */
    public void deleteUser(String passport) {
        users.remove(new User(passport, null));
    }

    /**
     * Метод принимает на вход номер паспорта и новый аккаунт.
     * Находит пользователя по паспорту.
     * И если пользователь найден, добавляет в список аккаунтов пользователя этот аккаунт.
     * @param passport Номер паспорта пользователя банка.
     * @param account Новый аккаунт пользователя банка.
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     * Метод принимает на вход номер паспорта ищет по карте и возвращает пользователя банка.
     * Если пользователь не найден возващает null.
     * @param passport Номер паспорта пользователя банка.
     * @return Пользователь банка.
     */
    public User findByPassport(String passport) {
        Set<User> allUsers = users.keySet();
        for (User user : allUsers) {
            if (user.getPassport().equals(passport)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Метод принимает на вход номер паспорта и реквезиты.
     * Ищет пользователя по номеру паспорта,
     * а затем ищет аккаунт по реквизиту из списка аккаунтов этого пользователя.
     * Если аккаунт не найден возвращает null.
     * @param passport Номер паспорта пользователя банка.
     * @param requisite Реквизиты одного из аккаунтов.
     * @return Аккаунт пользователя банка.
     */
    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            for (Account account : accounts) {
                if (account.getRequisite().equals(requisite)) {
                    return account;
                }
            }
        }
        return null;
    }

    /**
     * Метод ищет находит отправителя и получателя.
     * Проверяет возможно ли совершить перевод между аккаунтами с переданными реквизитами.
     * Осуществляет перевод денежных средств.
     * @param sourcePassport Номер паспорта отправителя.
     * @param sourceRequisite Реквизит счёта отпавителя.
     * @param destinationPassport Номер паспорта получателя.
     * @param destinationRequisite Реквизит счёта получателя.
     * @param amount Сумма для перечисления.
     * @return Результат перевода. Успешно или нет.
     */
    public boolean transferMoney(String sourcePassport, String sourceRequisite,
                                 String destinationPassport, String destinationRequisite,
                                 double amount) {
        boolean result = false;
        Account source = findByRequisite(sourcePassport, sourceRequisite);
        Account destination = findByRequisite(destinationPassport, destinationRequisite);
        if (source != null && destination != null && (source.getBalance() - amount) >= 0) {
            source.setBalance(source.getBalance() - amount);
            destination.setBalance(destination.getBalance() + amount);
            result = true;
        }
        return result;
    }

    /**
     * Метод возвращает список аккаунтов пользователя.
     * @param user Пользователь банка.
     * @return Список аккаунтов.
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
