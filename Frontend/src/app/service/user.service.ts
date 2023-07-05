import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';


@Injectable({
  providedIn: 'root'
})
export class UserService {
  private baseUrl = 'http://127.0.0.1:8081/socialnetworkapp/users';

  constructor(private http: HttpClient) { }

  getAllUsers(): Observable<User[]> {
  
    return this.http.get<User[]>(this.baseUrl);
   
  }

  getUserById(id: number): Observable<User> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.get<User>(url);
  }

  getUserByUsername(username: string): Observable<User> {
    const url = `${this.baseUrl}/username/${username}`;
    return this.http.get<User>(url);
  }

  getUserByEmail(email: string): Observable<User> {
    const url = `${this.baseUrl}/email/${email}`;
    return this.http.get<User>(url);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.baseUrl, user);
   
  }

  updateUser(id: number, user: User): Observable<User> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.put<User>(url, user);
  }

  deleteUser(id: number): Observable<void> {
    const url = `${this.baseUrl}/${id}`;
    return this.http.delete<void>(url);
  }
}
