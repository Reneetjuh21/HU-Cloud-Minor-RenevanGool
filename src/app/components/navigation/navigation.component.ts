import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from 'src/app/model/user';
import { LoginService } from 'src/app/services/login.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  currentUser: User;
  isLoggedIn$: Observable<boolean>;

  constructor(
    private loginService : LoginService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.currentUser = JSON.parse(localStorage.getItem('userLoginAngular'));
    this.isLoggedIn$ = this.loginService.isLoggedIn;
  }

  onLogOut() {
    this.loginService.logout();
    this.router.navigate(['/home']);
  }
}
