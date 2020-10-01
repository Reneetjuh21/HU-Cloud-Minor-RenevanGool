import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() { }

  isLoggedIn(): boolean {
    if (localStorage.getItem("userLoginAngular") === null) {
      return false;
    }

    return true;
  }
}
