
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../service/user.service';
import { User } from '../../model/user';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})

export class UserFormComponent {
  userForm: FormGroup;
  users!: User[];
  currentUser!: User;

  searchByUsernameForm: FormGroup;
  searchByEmailForm: FormGroup;
  searchResult!: User;

  // showAdd!:boolean;
  // showUpdate!:boolean;
  constructor(private formBuilder: FormBuilder, private userService: UserService) {
    this.userForm = this.formBuilder.group({
      id:[],
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(50)]],
      username: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(20)]],
      profilePicture: [''],
      bio: ['', Validators.maxLength(200)],
      location: ['', Validators.maxLength(100)],
      dateOfBirth: [''],
      gender: [''],
      interests: ['']
    });

    this.searchByUsernameForm = this.formBuilder.group({
      username: ['', Validators.required]
    });

    this.searchByEmailForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]]
    });

    this.getAllUsers();
  }


  createUser(): void {
    if (this.userForm.valid) {
      const newUser: User = {
        id:this.userForm.value.id, //added 
        name: this.userForm.value.name,
        username: this.userForm.value.username,
        email: this.userForm.value.email,
        password: this.userForm.value.password,
        profilePicture: this.userForm.value.profilePicture,
        bio: this.userForm.value.bio,
        location: this.userForm.value.location,
        dateOfBirth: this.userForm.value.dateOfBirth,
        gender: this.userForm.value.gender,
        interests: this.userForm.value.interests
      };

      this.userService.createUser(newUser).subscribe((user: User) => {
        this.users.push(user);   
        this.userForm.reset();
      });
    }
  }

  updateUser(): void {
    if (this.userForm.valid && this.currentUser) {
      const updatedUser: User = {
        id: this.currentUser.id,
        name: this.userForm.value.name,
        username: this.userForm.value.username,
        email: this.userForm.value.email,
        password: this.userForm.value.password,
        profilePicture: this.userForm.value.profilePicture,
        bio: this.userForm.value.bio,
        location: this.userForm.value.location,
        dateOfBirth: this.userForm.value.dateOfBirth,
        gender: this.userForm.value.gender,
        interests: this.userForm.value.interests
      };

      this.userService.updateUser(updatedUser.id, updatedUser).subscribe((user: User) => {
        const index = this.users.findIndex(u => u.id === user.id);
        if (index !== -1) {
          this.users[index] = user;
          this.userForm.reset();
          //this.currentUser = null;
        }
      });
    }
  }

  getUserById(userId: number): void {
    this.userService.getUserById(userId).subscribe((user: User) => {
      this.currentUser = user;
      this.userForm.patchValue(user);
    });
  }


  getAllUsers(): void {
    this.userService.getAllUsers().subscribe((users: User[]) => {
      this.users = users;
    });
  }

  deleteUser(userId: number): void {
    this.userService.deleteUser(userId).subscribe(() => {
      this.users = this.users.filter(user => user.id !== userId);
    });
  }


  searchByUsername(): void {
    if (this.searchByUsernameForm.valid) {
      const username = this.searchByUsernameForm.value.username;
      this.userService.getUserByUsername(username).subscribe((user: User) => {
        this.searchResult = user;
        this.searchByUsernameForm.reset();
      });
    }
  }

  searchByEmail(): void {
    if (this.searchByEmailForm.valid) {
      const email = this.searchByEmailForm.value.email;
      this.userService.getUserByEmail(email).subscribe((user: User) => {
        this.searchResult = user;
        this.searchByEmailForm.reset();
      });
    }
  }
}
