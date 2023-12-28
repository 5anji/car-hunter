<template>
<div class="login">
  <div class="signup__form">
    <div class="title">Welcome Back</div>
    <form autocomplete="off">
      <div class="signupFormElement">
        <label for="email">Email</label>
        <input
            v-model="myEmail"
            v-bind="email"
            id="email"
            placeholder="Type your email"
            type="email"
            class="login__input"
            :class="[ !errors.email && 'inputValid', !!errors.email && 'inputError' ]" />
      </div>
      <div class="signupFormElement">
      <label for="password">Password</label>
      <input
          v-model="myPass"
          v-bind="password"
          id="password"
          placeholder="Type your password"
          type="password"
          class="login__input"
          :class="[ !errors.password && 'inputValid', !!errors.password && 'inputError' ]" />
      </div>
      <div class="errors">
        <div class="description">
          {{ errors.email }}
        </div>
        <div class="description">
          {{ errors.password }}
        </div>
        </div>
      <div class="approveInfo">
        <div class="approveCheckbox">
          <input type="checkbox" id="remember" class="checkbox"/>
          <label for="remember">Remember me</label>
        </div>
        <div class="description">Forgot password?</div>
      </div>
      <ButtonCustom type="submit" id="loginSubmit" :text="'Log In'" @click="onSubmit"></ButtonCustom>
    </form>
    <div class="sign">
      <div class="description">Not a member yet?</div>
      <div class="description white" @click="toSignUp">Create account</div>
    </div>
  </div>
  <div class="signup__image">
    <img
        :src="login"
        alt="Image"
        class="loginImage"
    />
  </div>
</div>
</template>

<script setup>
import login from "../assets/images/login.svg"
import {useRouter} from "vue-router";
import {useForm} from "vee-validate";
import * as yup from 'yup';
import ButtonCustom from "@/elements/ButtonCustom.vue";
import {ref} from "vue";
const router = useRouter()

const { errors, handleSubmit, defineInputBinds } = useForm({
  validationSchema: yup.object({
    email: yup.string().email().required(),
    password: yup.string().required().matches(
        /^(?=.*[a-zA-Z])(?=.*\d).{9,}$/,
        "more than 8 characters, any letter and digit must be included"
    ),
  }),
});

const onSubmit = handleSubmit(onSuccess, onInvalidSubmit);

function onSuccess() {
  loginFunction();
}
function toSignUp() {
  router.push({name: 'signup'})
}
function onInvalidSubmit() {
  alert('Something went wrong');
}

const email = defineInputBinds('email');
const password = defineInputBinds('password');

const token = ref('');

let myEmail = "";
let myPass = "";

async function loginFunction() {
  console.log(myEmail)
  console.log(myPass)

  let postData = {
    email: myEmail,
    password: myPass,
  }
  let postDataNew = JSON.stringify(postData);

  console.log(postData);
  console.log(JSON.stringify(postData));
  try {
    const response = await fetch("http://localhost:8081/api/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: postDataNew,
    });
    const data = response.status;
    console.log(data);
    if (data === 201) {
      const data = await response.json();
       token.value = data["access-token"];
       localStorage.setItem( 'token', JSON.stringify(token) );
       console.log('Token:', token.value);
       await router.push({name: 'home'})
    }
  } catch (error) {
    console.error("Error:", error);
    alert('Something went wrong');
  }
}

</script>

<style lang="scss" scoped>
.login {
  width: 100%;
  max-width: 90rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
  height: 100vh;
  overflow: hidden;
  .signup__form{
    margin: 2.5rem;
    width: 100%;
    max-width: 27.5rem;
    display: grid;
    gap: 4.38rem;
    form {
      display: flex;
      flex-flow: column;
      gap: 1.88rem;

    .signupFormElement{
      display: flex;
      flex-flow: column;
      gap: 0.62rem;
      input {
        height: 3.13rem;
        background-color: var(--semiblue);
        display: flex;
        border-radius: 0.63rem;
        padding: 0 1.25rem;
      }
    }
      .errors{
        display: grid;
        gap: 0.5rem;
        .description{
          word-wrap: break-word;
          max-width: 27.5rem;
        }
      }
      .approveInfo{
        display: flex;
        justify-content: space-between;
        .approveCheckbox{
          display: flex;
          gap: 0.62rem;
        }
        .description{
          color: var(--white);
        }
      }
    }
    .sign {
      display: flex;
      justify-content: center;
      gap: 1rem;
      .white{
        color: var(--white);
        cursor: pointer;
      }
    }
  }
  .signup__image{
    .loginImage{
      width: 100%;
      height: 100vh;
      object-fit: cover;
    }
  }
}
</style>