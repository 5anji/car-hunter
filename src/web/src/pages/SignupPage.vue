<template>
  <div class="signup">
    <div class="signup__image">
      <img
          :src="signup"
          alt="Image"
          class="loginImage"
      />
    </div>
    <div class="signup__form">
      <div class="title">Register</div>
      <form autocomplete="off">
        <div class="signupFormElement">
          <label for="email">Email address</label>
          <input
              v-bind="email"
              id="email"
              placeholder="Enter your email"
              type="email"
              class="login__input"
              :class="[ !errors.email && 'inputValid', !!errors.email && 'inputError' ]" />
        </div>
        <div class="signupFormElement">
          <label for="password">Create password</label>
          <input
              v-bind="password"
              id="password"
              placeholder="Enter password"
              type="password"
              class="login__input"
              :class="[ !errors.password && 'inputValid', !!errors.password && 'inputError' ]" />
        </div>
        <div class="signupFormElement">
          <label for="passwordConfirm">Confirm password</label>
          <input
              v-bind="password"
              id="passwordConfirm"
              placeholder="Confirm password"
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
            <input type="checkbox" id="approve" class="checkbox"/>
            <label for="approve">I agree with</label>
          </div>
          <div class="description">Terms and Conditions and Privacy Policy</div>
        </div>
        <ButtonCustom type="submit" id="loginSubmit" :text="'Sign Up'" @click="onSubmit"></ButtonCustom>
      </form>
      <div class="sign">
        <div class="description">Have an account?</div>
        <div class="description white" @click="toLogIn">Log In</div>
      </div>
    </div>
  </div>
</template>

<script setup>
import signup from "../assets/images/signup.svg"
import {useRouter} from "vue-router";
import {useForm} from "vee-validate";
import * as yup from 'yup';
import ButtonCustom from "@/elements/ButtonCustom.vue";
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
  router.push({name: 'home'})
}
function toLogIn() {
  router.push({name: 'login'})
}
function onInvalidSubmit() {
}

const email = defineInputBinds('email');
const password = defineInputBinds('password');
</script>

<style lang="scss" scoped>
.signup {
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
        gap: 0.6rem;
        .approveCheckbox{
          display: flex;
          gap: 0.62rem;
          color: var(--gray);
          font-weight: 400;
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