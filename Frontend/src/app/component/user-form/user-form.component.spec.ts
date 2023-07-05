import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HttpTestingController } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { UserFormComponent } from './user-form.component';
import { UserService } from '../../service/user.service';
import { User } from '../../model/user';

describe('UserComponent', () => {
    let component: UserFormComponent;
    let fixture: ComponentFixture<UserFormComponent>;
    let serviceMock:any;
    let userService: UserService;

    const user: User = {
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
    }
     //to check return value on funciton call
    let mockService = {
      getAllUsers: ()=>{
        return of([user])
      },
      deleteUser: (id:number|string)=>{
        return of(user)
      },    
      getUserById: ()=>{
        return of([user])
      },

      // createUser: ()=>{
      //   return of([user])
      // },  
      // updateUser: (id:number|string)=>{
      //   return of(user)
      // },
 
      // searchByUsername: ()=>{
      //   return of([user])
      // },
      // searchByEmail: ()=>{
      //   return of([user])
      // },

    }

    beforeEach(async () => {
      await TestBed.configureTestingModule({
        declarations: [UserFormComponent],
        imports: [FormsModule,ReactiveFormsModule,HttpClientModule] ,
        providers: [FormBuilder, UserService,HttpTestingController,{provide: UserService, useValue: mockService}] //
      }).compileComponents();
    });

    beforeEach(() => {

      serviceMock={
        getUserById:jest.fn(),
        getAllUsers:jest.fn(),
        createUser:jest.fn(),
        updateUser:jest.fn(),
        deleteUser:jest.fn(),
        getUserByUsername:jest.fn(),
        getUserByEmail:jest.fn(),
        };

      fixture = TestBed.createComponent(UserFormComponent);
      component = fixture.componentInstance;
      userService = TestBed.inject(UserService);
      fixture.detectChanges();
    });

    describe("business", ()=>{

      it('should create the user component', () => {
        expect(component).toBeTruthy();
      });

      it('should declare user form',()=>{
        expect(component.userForm).toBeDefined();  
      })

      it('should initialize the form',()=>{
        const userForm={
          id: null,
          name:'',
          username:'',
          email:'',
          password:'',
          profilePicture:'',
          bio:'',
          location:'',
          dateOfBirth:'',
          gender:'',
          interests:''
        };
        expect(component.userForm.value).toEqual(userForm);
      });
   });

   
   describe('business',()=>{       

    it('should validate the name field in the form', () => {
      const nameControl = component.userForm.controls['name'];        
      // Set a valid value for the name field
      nameControl.setValue('Venu');    
      // Check if the name field is valid 
      expect(nameControl.valid).toBeTruthy();    
      // Set an invalid value for the name field (empty string)
      nameControl.setValue('');    
      // Check if the name field is invalid
      expect(nameControl.invalid).toBeTruthy();    
      // Set an invalid value for the name field (less than 3 characters)
      nameControl.setValue('Ve');    
      // Check if the name field is invalid
      expect(nameControl.invalid).toBeTruthy();
    });

    it('should validate the username field in the form', () => {
      const c = component.userForm.controls['username'];        
     c.setValue(500);    
      expect(c.valid).toBeTruthy();    
     c.setValue('');    
      expect(c.invalid).toBeTruthy();   
      c.setValue('Ve');  
      expect(c.invalid).toBeTruthy();
    });

    it('should validate the email field in the form', () => {
      const c = component.userForm.controls['email'];        
     c.setValue('venu@gmail.com');    
      expect(c.valid).toBeTruthy();    
     c.setValue('');    
      expect(c.invalid).toBeTruthy();   
      c.setValue('abc');  
      expect(c.invalid).toBeTruthy();
    });

    it('should validate the password field in the form', () => {
      const c = component.userForm.controls['password'];        
     c.setValue('mypassword');    
      expect(c.valid).toBeTruthy();    
     c.setValue('');    
      expect(c.invalid).toBeTruthy();   
      c.setValue('pwd');  
      expect(c.invalid).toBeTruthy();
    });

    it('should validate the profilePicture field in the form', () => {
      const c = component.userForm.controls['profilePicture'];        
     c.setValue('mypic');    
      expect(c.valid).toBeTruthy();    
     c.setValue('');    
      expect(c.valid).toBeTruthy();  

    });
    
    it('should validate the bio field in the form', () => {
      const c = component.userForm.controls['bio'];        
     c.setValue('mybio');    
      expect(c.valid).toBeTruthy();    
     c.setValue('');    
      expect(c.valid).toBeTruthy();  

    });
    
    it('should validate the location field in the form', () => {
      const c = component.userForm.controls['location'];        
     c.setValue('Hyderabad');    
      expect(c.valid).toBeTruthy();    
     c.setValue('');    
      expect(c.valid).toBeTruthy();   
    });
    
    it('should validate the dateOfBirth field in the form', () => {
      const c = component.userForm.controls['dateOfBirth'];        
     c.setValue('mypassword');    
      expect(c.valid).toBeTruthy();    
     c.setValue(new Date('2000-01-01'));    
      expect(c.valid).toBeTruthy();  

    });
        
    it('should validate the gender field in the form', () => {
      const c = component.userForm.controls['gender'];        
     c.setValue('MALE');    
      expect(c.valid).toBeTruthy();    
     c.setValue('');    
      expect(c.valid).toBeTruthy();   
    });
        
    it('should validate the interests field in the form', () => {
      const c = component.userForm.controls['interests'];        
     c.setValue('reading books');    
      expect(c.valid).toBeTruthy();    
     c.setValue('');    
      expect(c.valid).toBeTruthy();   
    });

  });


describe("boundary", ()=>{

  it('should invalidate the form when name length  is greater than 50', () => {
    const form = component.userForm;
    form.controls['name'].setValue('aaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaa bbbbbbbbbbbbbbbbb ccccccccccccccccccccccccccc');
    expect(form.invalid).toBeTruthy();
    expect(form.controls['name'].errors?.['maxlength']).toBeTruthy();
  });
  it('should invalidate the form when user name length  is greater than 20', () => {
    const form = component.userForm;
    form.controls['username'].setValue('aaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaa  ccccccccccccccccccccccccccc');
    expect(form.invalid).toBeTruthy();
    expect(form.controls['username'].errors?.['maxlength']).toBeTruthy();
  });

  it('should invalidate the form when name length is less than 3', () => {
    const form = component.userForm;
    form.controls['name'].setValue('Pr');
    expect(form.invalid).toBeTruthy();
    expect(form.controls['name'].errors?.['minlength']).toBeTruthy();
  });

  it('should invalidate the form when user name length is less than 3', () => {
    const form = component.userForm;
    form.controls['username'].setValue('Pr');
    expect(form.invalid).toBeTruthy();
    expect(form.controls['username'].errors?.['minlength']).toBeTruthy();
  });


  it('should invalidate the form when password length  is greater than 20', () => {
    const form = component.userForm;
    form.controls['password'].setValue('aaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaa  ccccccccccccccccccccccccccc');
    expect(form.invalid).toBeTruthy();
    expect(form.controls['password'].errors?.['maxlength']).toBeTruthy();
  });


  it('should invalidate the form when password length is less than 8', () => {
    const form = component.userForm;
    form.controls['password'].setValue('abcdef');
    expect(form.invalid).toBeTruthy();
    expect(form.controls['password'].errors?.['minlength']).toBeTruthy();
  });

  it('should invalidate the form when bio length  is greater than 200', () => {
    const form = component.userForm;
    form.controls['bio'].setValue('aaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaa  ccccccccccccccccccccccccccc iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiioooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo');
    expect(form.invalid).toBeTruthy();
    expect(form.controls['bio'].errors?.['maxlength']).toBeTruthy();
  });

  it('should invalidate the form when location length  is greater than 100', () => {
    const form = component.userForm;
    form.controls['location'].setValue('aaaaaaaaaaaaaaaaaaaaaaaaaaaa aaaaaaaaaaaa  ccccccccccccccccccccccccccc iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiioooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo');
    expect(form.invalid).toBeTruthy();
    expect(form.controls['location'].errors?.['maxlength']).toBeTruthy();
  });


  it('should validate the valid form on valid values to all fields',()=>{
    component.userForm.controls['id'].setValue('1');
    component.userForm.controls['name'].setValue('myname');
    component.userForm.controls['username'].setValue('myusername');
    component.userForm.controls['email'].setValue('abc@yahoo.com');
    component.userForm.controls['password'].setValue('pwd123456789');
    component.userForm.controls['profilePicture'].setValue('my pic');
    component.userForm.controls['bio'].setValue('my bio');
    component.userForm.controls['location'].setValue('HYd');
    component.userForm.controls['dateOfBirth'].setValue(new Date('1990-01-01'));
    component.userForm.controls['gender'].setValue('MALE');
    component.userForm.controls['interests'].setValue('intr');
    expect(component.userForm.valid).toBeTruthy();
  });

  it('should enable the submit button when the form is valid', () => {
    component.userForm.controls['name'].setValue('myname');
    component.userForm.controls['username'].setValue('myusername');
    component.userForm.controls['email'].setValue('abc@yahoo.com');
    component.userForm.controls['password'].setValue('pwd123456789');
    component.userForm.controls['profilePicture'].setValue('my pic');
    component.userForm.controls['bio'].setValue('my bio');
    component.userForm.controls['location'].setValue('HYd');
    component.userForm.controls['dateOfBirth'].setValue(new Date('1990-01-01'));
    component.userForm.controls['gender'].setValue('MALE');
    component.userForm.controls['interests'].setValue('intr');
    fixture.detectChanges();
    
    const submitButton = fixture.nativeElement.querySelector('button[type="button"]');
    expect(submitButton.disabled).toBe(false);
  });

  //Test Passing but showing console error in Terminal

  // it('should call createUser method when the form is submitted', () => {  
  //   jest.spyOn(component, 'createUser');
  //   component.userForm.controls['name'].setValue('myname');
  //   component.userForm.controls['username'].setValue('myusername');
  //   component.userForm.controls['email'].setValue('abc@yahoo.com');
  //   component.userForm.controls['password'].setValue('pwd123456789');
  //   component.userForm.controls['profilePicture'].setValue('my pic');
  //   component.userForm.controls['bio'].setValue('my bio');
  //   component.userForm.controls['location'].setValue('HYd');
  //   component.userForm.controls['dateOfBirth'].setValue(new Date('1990-01-01'));
  //   component.userForm.controls['gender'].setValue('MALE');
  //   component.userForm.controls['interests'].setValue('intr');
  //   fixture.detectChanges();    
  //   const submitButton = fixture.nativeElement.querySelector('button[type="button"]');
  //   submitButton.click();    
  //   expect(component.createUser).toHaveBeenCalled();
  // });

 });


 
 describe('exception',()=>{

  it('should invalidate the form when empty',()=>{
    component.userForm.controls['name'].setValue('');
    component.userForm.controls['username'].setValue('');
    component.userForm.controls['email'].setValue('');
    component.userForm.controls['password'].setValue('');
    component.userForm.controls['profilePicture'].setValue('');
    component.userForm.controls['bio'].setValue('');
    component.userForm.controls['location'].setValue('');
    component.userForm.controls['dateOfBirth'].setValue('');
    component.userForm.controls['gender'].setValue('');
    component.userForm.controls['interests'].setValue('');
    expect(component.userForm.valid).toBeFalsy();
  });

   
  it('name field should show required error when no value is provided', () => {
    const c = component.userForm.controls['name']
    expect(c.valid).toBeFalsy();      
    c.setValue('');
    expect(c.hasError('required')).toBeTruthy();      
  });

  it('user name field should show required error when no value is provided', () => {
    const c = component.userForm.controls['username']
    expect(c.valid).toBeFalsy();      
    c.setValue('');
    expect(c.hasError('required')).toBeTruthy();      
  });
  it('email field should show required error when no value is provided', () => {
    const c = component.userForm.controls['email']
    expect(c.valid).toBeFalsy();      
    c.setValue('');
    expect(c.hasError('required')).toBeTruthy();      
  });
  
  it('password field should show required error when no value is provided', () => {
    const c = component.userForm.controls['password']
    expect(c.valid).toBeFalsy();      
    c.setValue('');
    expect(c.hasError('required')).toBeTruthy();      
  });    
  
});

describe('business',()=>{

  it('should fetch all users', ()=>{    
    component.users=[];
    jest.spyOn(mockService, 'getAllUsers').mockReturnValue(of([user]));
    component.getAllUsers();      
    expect(mockService.getAllUsers).toBeCalledTimes(1);
    expect(component.users.length).toBeGreaterThan(0);

    expect(Array.isArray(component.users)).toBe(true);
  })

  it('should delete user by id', ()=>{  
    jest.spyOn(mockService, 'deleteUser').mockReturnValue(of(user));
    component.deleteUser(1);      
    expect(mockService.deleteUser).toBeCalledTimes(1);
    expect(mockService.deleteUser).toBeCalledWith(1);

  })

  it('should get user  by id', ()=>{  
    jest.spyOn(mockService, 'getUserById')//.mockReturnValue(of(user));
    component.getUserById(1);      
    expect(mockService.getUserById).toBeCalledTimes(1);
    expect(mockService.getUserById).toBeCalledWith(1);

  })

  // it('should search by user name', ()=>{  
  //   jest.spyOn(mockService, 'searchByUsername')//.mockReturnValue(of(user));
  //   component.searchByUsername();      
  //   // expect(mockService.searchByUsername).toBeCalledTimes(1);
  //   expect(mockService.searchByUsername).toBeCalledWith("username1");
  // })

  // it('should search by email', ()=>{  
  //   jest.spyOn(mockService, 'searchByEmail')//.mockReturnValue(of(user));
  //   component.searchByEmail();      
  //   // expect(mockService.searchByEmail).toBeCalledTimes(1);
  //   expect(mockService.searchByEmail).toBeCalledWith("abc@yahoo.com");
  // })

})

});

//Getting always pass (need to re write)

// describe('business',()=>{

//   it('should get all users',()=>{ 
//     const response={
//       success:true,
//       message:'all users get successfully'
//     };
//     const editPro=jest
//     .spyOn(serviceMock,'getAllUsers')
//     .mockReturnValue(response);
//     expect(serviceMock.getAllUsers(user)).toBe(response);
//     expect(editPro).toHaveBeenCalled();
//     })

//    it('should get a user by id',()=>{ 
//     const response={
//       success:true,
//       message:'a user get successfully'
//     };
//     const editPro=jest
//     .spyOn(serviceMock,'getUserById')
//     .mockReturnValue(response);
//     expect(serviceMock.getUserById(1)).toBe(response);
//     expect(editPro).toHaveBeenCalledWith(1);
//     })
    
//   it('should add the user',()=>{ 
//     const response={
//       success:true,
//       message:'user added successfully'
//     };
//     const editPro=jest
//     .spyOn(serviceMock,'createUser')
//     .mockReturnValue(response);
//     expect(serviceMock.createUser(user)).toBe(response);
//     expect(editPro).toHaveBeenCalledWith(user);
//     })

//     it('should update the user of specified id',()=>{ 
//       const response={
//         success:true,
//         message:'user updated successfully'
//       };
//       const editPro=jest
//       .spyOn(serviceMock,'updateUser')
//       .mockReturnValue(response);
//       expect(serviceMock.updateUser(user)).toBe(response);
//       expect(editPro).toHaveBeenCalledWith(user);
//       })

//       it('should delete the user of specified id',()=>{   
//           const response={
//             success:true,
//             message:'user deleted successfully'
//           };
//           const delPro=jest
//           .spyOn(serviceMock,'deleteUser')
//           .mockReturnValue(response);
//           expect(serviceMock.deleteUser(1)).toBe(response);
//           expect(delPro).toHaveBeenCalledWith(1);
//       })

//       it('should get a user by user name',()=>{ 
//         const response={
//           success:true,
//           message:'a user get successfully'
//         };
//         const editPro=jest
//         .spyOn(serviceMock,'getUserByUsername')
//         .mockReturnValue(response);
//         expect(serviceMock.getUserByUsername('abcd')).toBe(response);
//         expect(editPro).toHaveBeenCalledWith('abcd');
//         })

//         it('should get a user by email',()=>{ 
//           const response={
//             success:true,
//             message:'a user get successfully'
//           };
//           const editPro=jest
//           .spyOn(serviceMock,'getUserByEmail')
//           .mockReturnValue(response);
//           expect(serviceMock.getUserByEmail('abcd@yahoo.com')).toBe(response);
//           expect(editPro).toHaveBeenCalledWith('abcd@yahoo.com');
//           })
//   });  


  //wont works in other systems or if specified records not available in db

  // describe("business",()=>{

  //   it('should call service getAllUsers method on initialization', () => {
  //       const users: User[] = [{
  //         id: 1,
  //         name:'user1',
  //         username:'username1',
  //         email:'abc@yahoo.com',
  //         password:'abc12345678',
  //         profilePicture:'pic',
  //         bio:'bio',
  //         location:'hyd',
  //         dateOfBirth:new Date('1990-01-01'),
  //         gender:'MALE',
  //         interests:'inter'
  //       }];
  //     const getAllUsersSpy = jest.spyOn(userService, 'getAllUsers')//.mockReturnValue(of(users));  

  //     //expect(getAllUsersSpy).toHaveBeenCalled(); //Error ?
  //    // expect(getAllUsersSpy).toHaveBeenCalledTimes(0);// 0 ?
  //     expect(component.users).toEqual(users);
  //   });

  //   it('should call service createUser and update the User list', () => {     
  //     const user: User = {
  //       id: 1,
  //       name:'user1',
  //       username:'username1',
  //       email:'abc@yahoo.com',
  //       password:'abc12345678',
  //       profilePicture:'pic',
  //       bio:'bio',
  //       location:'hyd',
  //       dateOfBirth:new Date('1990-01-01'),
  //       gender:'MALE',
  //       interests:'inter'
  //     };     
  //     userService.createUser = jest.fn().mockReturnValue(of(user));      
  //     component.createUser();        
  //     //expect(userService.createUser).toHaveBeenCalledTimes(0);// 0 ?
  //     expect(component.users).toContainEqual(user);
  //   });      
         
  //   it('should call service updateUser and update the user list', () => {
  //     const updatedUser: User = {
  //       id:1,
  //       name:'user1',
  //       username:'username1',
  //       email:'abc@yahoo.com',
  //       password:'abc12345678',
  //       profilePicture:'pic',
  //       bio:'bio',
  //       location:'hyd',
  //       dateOfBirth:new Date('1990-01-01'),
  //       gender:'MALE',
  //       interests:'inter'
  //     };
  //     userService.updateUser = jest.fn().mockReturnValue(of(updatedUser));    
  //     component.updateUser();    
  //     // expect(userService.updateUser).toHaveBeenCalledWith(updatedUser.id,updatedUser);
  //    // expect(userService.updateUser).toHaveBeenCalledTimes(0); //  0 ?

  //     expect(component.users).toContainEqual(updatedUser);
  //   });

    
  //   it('should call service deleteUser and remove the user from the user list', () => {
  //     const userToDelete: User = {
  //       id: 1,
  //       name:'user1',
  //       username:'username1',
  //       email:'abc@yahoo.com',
  //       password:'abc12345678',
  //       profilePicture:'pic',
  //       bio:'bio',
  //       location:'hyd', 
  //       dateOfBirth:new Date('1990-01-01'),
  //       gender:'MALE',
  //       interests:'inter'
  //     };
  //     const deleteUserSpy = jest.spyOn(userService, 'deleteUser')//.mockReturnValue(of(undefined));
  //     component.deleteUser(userToDelete.id);
  //     // expect(deleteUserSpy).toHaveBeenCalledTimes(1);
  //     expect(component.users).not.toContain(userToDelete);
  //   });
  //   //search
  // });



//chat gpt test cases

// describe('business',()=>{

  // it('should call the UserService to get all users', () => {
  //   const users: User[] = [
  //     { id: 1, name: 'John Doe', username: 'johndoe', email: 'johndoe@example.com' },
  //     { id: 2, name: 'Jane Smith', username: 'janesmith', email: 'janesmith@example.com' }
  //   ];

  //   spyOn(userService, 'getAllUsers').and.returnValue(of(users));

  //   component.ngOnInit();

  //   expect(userService.getAllUsers).toHaveBeenCalled();
  //   expect(component.users).toEqual(users);
  // });

  // it('should call the UserService to create a new user', () => {
  //   const newUser: User = { name: 'John Doe', username: 'johndoe', email: 'johndoe@example.com' };

  //   spyOn(userService, 'createUser').and.returnValue(of(newUser));

  //   component.createUser(newUser);

  //   expect(userService.createUser).toHaveBeenCalledWith(newUser);
  //   expect(component.users).toContain(newUser);
  // });

  // it('should call the UserService to update an existing user', () => {
  //   const updatedUser: User = { id: 1, name: 'Updated User', username: 'updateduser', email: 'updateduser@example.com' };

  //   spyOn(userService, 'updateUser').and.returnValue(of(updatedUser));

  //   component.updateUser(updatedUser);

  //   expect(userService.updateUser).toHaveBeenCalledWith(updatedUser.id, updatedUser);
  //   expect(component.users).toContain(updatedUser);
  // });

  // it('should call the UserService to delete a user', () => {
  //   const userId = 1;

  //   spyOn(userService, 'deleteUser').and.returnValue(of(null));

  //   component.deleteUser(userId);

  //   expect(userService.deleteUser).toHaveBeenCalledWith(userId);
  //   expect(component.users.length).toBe(1);
  //   expect(component.users.find(user => user.id === userId)).toBeFalsy();
  // });

//   it('should call the UserService to search users by username', () => {
//     const searchTerm = 'user1';
//     const users: User = {
//       id: 1,
//       name:'user1',
//       username:'username1',
//       email:'abc@yahoo.com',
//       password:'abc12345678',
//       profilePicture:'pic',
//       bio:'bio',
//       location:'hyd', 
//       dateOfBirth:new Date('1990-01-01'),
//       gender:'MALE',
//       interests:'inter'
//     };
//     const getUserByUsernameSpy = jest.spyOn(userService, 'getUserByUsername').mockReturnValue(of(users));
   
//     component.searchByUsername();
//     expect(getUserByUsernameSpy).toHaveBeenCalled();
//     //expect(userService.getUserByUsername).toHaveBeenCalledWith(searchTerm);

   
//     expect(component.users).toEqual(users);
//   });

//   it('should call the UserService to search users by email', () => {
//     const searchTerm = 'abc@yahoo.com';

//     const users: User = {
//       id: 1,
//       name:'user1',
//       username:'username1',
//       email:'abc@yahoo.com',
//       password:'abc12345678',
//       profilePicture:'pic',
//       bio:'bio',
//       location:'hyd', 
//       dateOfBirth:new Date('1990-01-01'),
//       gender:'MALE',
//       interests:'inter'
//     };
//     const getUserByEmailSpy = jest.spyOn(userService, 'getUserByEmail').mockReturnValue(of(users));

//     component.searchByEmail();
//     expect(getUserByEmailSpy).toHaveBeenCalled();
//     //expect(userService.getUserByEmail).toHaveBeenCalledWith(searchTerm);

//     expect(component.users).toEqual(users);
//   });
// })


//These were working before curd and search methods test case implementation





