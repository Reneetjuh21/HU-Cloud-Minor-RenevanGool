import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login.service';
import {Router} from '@angular/router';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  errorMessage: string;
  form: FormGroup;
  submitted = false;

  constructor(private loginService: LoginService, private router: Router, private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.form = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get formControls() { return this.form.controls; }

  public login(): void {
    this.submitted = true;
    this.errorMessage = undefined;

    if (this.form.invalid) {
      return;
    }

    var result = this.loginService.login(this.formControls.username.value, this.formControls.password.value);

    if (result) {
      this.router.navigate(['/user-details']);
    } else {
      this.errorMessage = "You tried to login with incorrect credentials!";
    }
  }
}