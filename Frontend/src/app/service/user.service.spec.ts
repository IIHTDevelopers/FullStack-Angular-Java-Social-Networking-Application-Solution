import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { UserService } from './user.service';
import { of } from 'rxjs';

describe('UserService', () => {
  let userService: UserService;
  let httpTestingController: HttpTestingController;
  let httpClientSpy: any;
  let service:UserService;
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [UserService]
    });
    userService = TestBed.inject(UserService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });
  
  beforeEach(() => {
    httpClientSpy = {
      get: jest.fn(),
      post: jest.fn(),
      put: jest.fn(),
      delete: jest.fn(), 
    };
    service = TestBed.get(UserService); //or blow one 
    userService = new UserService(httpClientSpy);//testing service method functionality should use this reference only

  });
  afterEach(() => {
    httpTestingController.verify();
  });

  describe('business',()=>{
    
  it('user service should be created', () => {
    expect(service).toBeTruthy(); 
   });

    it('should get all users by calling getAllUsers() in service', () => {
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/socialnetworkapp/users';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res)); //of represent returns Observable
      userService.getAllUsers();
      // expect(httpClientSpy.get).toBeCalledTimes(1); //Testing whether get method called
      expect(httpClientSpy.get).toHaveBeenCalledWith(url); //Testing whether get passing with url or not
    });
    
    it('should get user by calling getUserById() in service', () => {
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/socialnetworkapp/users/1';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res)); //of represent returns Observable
      userService.getUserById(1);
      // expect(httpClientSpy.get).toBeCalledTimes(1); //Testing whether get method called
      expect(httpClientSpy.get).toHaveBeenCalledWith(url); //Testing whether get passing with url or not
    });

    it('should create user by calling createUser() in service', () => {
      const data = {
        id: 1,
        name:'user1',
        username:'username1',
        email:'abc@yahoo.com',
        password:'abc12345678',
        profilePicture:'pic',
        bio:'bio',
        location:'hyd',
        dateOfBirth:new Date('1990-01-01'),
        gender:'MALE',
        interests:'inter'
       };
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/socialnetworkapp/users';
      jest.spyOn(httpClientSpy, 'post').mockReturnValue(of(res));
      userService.createUser(data);
      // expect(httpClientSpy.post).toBeCalledTimes(1);
      expect(httpClientSpy.post).toHaveBeenCalledWith(url,data);
    });
  
    it('should update user by calling updateUser() in service', () => {
      const command1 = 1;
      const data = {
        id: 1,
        name:'user1',
        username:'username1',
        email:'abc@yahoo.com',
        password:'abc12345678',
        profilePicture:'pic',
        bio:'bio',
        location:'hyd',
        dateOfBirth:new Date('1990-01-01'),
        gender:'MALE',
        interests:'inter'
      };
  
      const res = 'some message';
      const url = 'http://127.0.0.1:8081/socialnetworkapp/users/1';
      jest.spyOn(httpClientSpy, 'put').mockReturnValue(of(res));
      userService.updateUser(1,data);
      // expect(httpClientSpy.put).toBeCalledTimes(1);
      expect(httpClientSpy.put).toHaveBeenCalledWith(url,data);
    });
  
    it('should delete user by calling deleteUser() in service', () => {
      const command = 1;
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/socialnetworkapp/users/1';
      jest.spyOn(httpClientSpy, 'delete').mockReturnValue(of(res));
      userService.deleteUser(command);
      // expect(httpClientSpy.delete).toBeCalledTimes(1);
      expect(httpClientSpy.delete).toHaveBeenCalledWith(API_URL);
    });

    it('should search user with username by calling getUserByUsername() in service', () => {
      const command = 1;
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/socialnetworkapp/users';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      userService.getUserByUsername('user1');
      // expect(httpClientSpy.get).toBeCalledTimes(1);
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL+'/username'+'/user1');
    });

    it('should search user with email by calling getUserByEmail() in service', () => { 
      const command = 1;
      const res = 'some message';
      const API_URL = 'http://127.0.0.1:8081/socialnetworkapp/users';
      jest.spyOn(httpClientSpy, 'get').mockReturnValue(of(res));
      userService. getUserByEmail('user1@gmail.com');
      // expect(httpClientSpy.get).toBeCalledTimes(1);
      expect(httpClientSpy.get).toHaveBeenCalledWith(API_URL+'/email'+'/user1@gmail.com');
    });
   
   });  

});





//Chat GPT test cases- Need to modify

  // it('should create a new user', () => {
  //   const newUser: User = {
  //     id:1,
  //     name: 'John Doe',
  //     username: 'johndoe',
  //     email: 'johndoe@example.com',
  //     password: 'password123',
  //     profilePicture: 'pic',
  //     bio: 'bio',
  //     location: 'Hyd',  
  //     dateOfBirth: new Date('1990-01-01'),
  //     gender: 'MALE',
  //     interests: 'Yes'
  //   };

  //   userService.createUser(newUser).subscribe((user: User) => {
  //     expect(user).toEqual(newUser);
  //   });

  //   const req = httpTestingController.expectOne('/socialnetworkapp/users');
  //   expect(req.request.method).toEqual('POST');
  //   req.flush(newUser);
  // });

  // it('should update an existing user', () => {
  //   const updatedUser: User = {
  //     id: 1,
  //     name: 'Updated User',
  //     username: 'updateduser',
  //     email: 'updateduser@example.com',
  //     password: 'updatedpassword',
  //     profilePicture: 'pic',
  //     bio: 'bio',
  //     location: 'Hyd',  
  //     dateOfBirth: new Date('1990-01-01'),
  //     gender: 'MALE',
  //     interests: 'Yes'

  //   };




  //   userService.updateUser(updatedUser.id, updatedUser).subscribe((user: User) => {
  //     expect(user).toEqual(updatedUser);
  //   });

  //   const req = httpTestingController.expectOne(`/socialnetworkapp/users/${updatedUser.id}`);
  //   expect(req.request.method).toEqual('PUT');
  //   req.flush(updatedUser);
  // });

  // it('should get a user by ID', () => {
  //   const userId = 1;
  //   const user: User = {
  //     id: userId,
  //     name: 'John Doe',
  //     username: 'johndoe',
  //     email: 'johndoe@example.com',
  //     password: 'password123',
  //     profilePicture: 'pic',
  //     bio: 'bio',
  //     location: 'Hyd',  
  //     dateOfBirth: new Date('1990-01-01'),
  //     gender: 'MALE',
  //     interests: 'Yes'
  //   };

  //   userService.getUserById(userId).subscribe((result: User) => {
  //     expect(result).toEqual(user);
  //   });

  //   const req = httpTestingController.expectOne(`/socialnetworkapp/users/${userId}`);
  //   expect(req.request.method).toEqual('GET');
  //   req.flush(user);
  // });

  // it('should get all users', () => {
  //   const users: User[] = [
  //     {
  //       id: 1,
  //       name: 'John Doe',
  //       username: 'johndoe',
  //       email: 'johndoe@example.com',
  //       password: 'password123',
  //       profilePicture: 'pic',
  //       bio: 'bio',
  //       location: 'Hyd',  
  //       dateOfBirth: new Date('1990-01-01'),
  //       gender: 'MALE',
  //       interests: 'Yes'
  //     },
  //     {
  //       id: 2,
  //       name: 'Jane Smith',
  //       username: 'janesmith',
  //       email: 'janesmith@example.com',
  //       password: 'password456',
  //       profilePicture: 'pic',
  //       bio: 'bio',
  //       location: 'Hyd',  
  //       dateOfBirth: new Date('1990-01-01'),
  //       gender: 'MALE',
  //       interests: 'Yes'
  //     }
  //   ];

  //   userService.getAllUsers().subscribe((result: User[]) => {
  //     expect(result).toEqual(users);
  //   });

  //   const req = httpTestingController.expectOne('/socialnetworkapp/users');
  //   expect(req.request.method).toEqual('GET');
  //   req.flush(users);
  // });

  // it('should delete a user', () => {
  //   const userId = 1;

  //   userService.deleteUser(userId).subscribe(() => {
  //     expect().nothing();
  //   });

  //   const req = httpTestingController.expectOne(`/api/users/${userId}`);
  //   expect(req.request.method).toEqual('DELETE');
  //   req.flush({});
  // });




