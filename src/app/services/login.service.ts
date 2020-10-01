import { Injectable } from '@angular/core';
import { User } from '../model/user';
import { Observable, BehaviorSubject } from 'rxjs';
import { USERS } from '../mock/mock-users';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private loggedIn = new BehaviorSubject<boolean>(false);

  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

  constructor() { }

  login(username: string, password: string) {
    var user = this.getUserByUsername(username);

    if (user.password !== password) {
      return false;
    }

    localStorage.setItem('userLoginAngular', JSON.stringify(user));
    this.loggedIn.next(true);
    return true;
  }

  logout() {
    localStorage.removeItem('userLoginAngular');
    this.loggedIn.next(false);
  }

  private getUsers(): User[] {
    return USERS;
  }

  private getUserByUsername(username:string): User {
    return USERS.find(user => user.username == username);
  }
}
