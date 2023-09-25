<template> 
  <div class="login__page">
    <div class="login__block">
      <h1>Login</h1>
      <div class="login__form">
        <form @submit="onSubmit">
          <div>
            <label for="email">Email</label>
            <input
              v-bind="email"
              id="email"
              placeholder="Type your email"
              type="email"
              class="login__input"
              :class="{valid: meta.touched && meta.valid, invalid: meta.touched && !meta.valid }"
            >
          </div>

          <div>
            <label for="password">Password</label>
            <input
              v-bind="password"
              id="password"
              placeholder="Type your password"
              type="password"
              min="6"
              class="login__input"
              :class="{valid: meta.touched && meta.valid, invalid: meta.touched && !meta.valid }"
            >
          </div>

          <div>
            <label for="submit" />
            <input
              id="submit"
              type="submit"
              class="login__button"
            >
          </div>

          <div>{{ errors.email }}</div>
          <div>{{ errors.password }}</div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
import {useForm} from 'vee-validate';
import * as yup from 'yup';
import {useRouter} from "vue-router";
const router = useRouter()

const { meta, errors, handleSubmit, defineInputBinds } = useForm({
  validationSchema: yup.object({
    email: yup.string().email().required(),
    password: yup.string().min(6).required(),
  }),
});

const onSubmit = handleSubmit(onSuccess, onInvalidSubmit);

function onSuccess() {
  router.push({name: 'page1'})
  // alert(JSON.stringify(values, null, 2));
}
function onInvalidSubmit() {
  // alert(JSON.stringify(errors.values, null, 2));

}

const email = defineInputBinds('email');
const password = defineInputBinds('password');

</script>

<style lang="scss" scoped>
h1 {
  color: var(--black)
}

.login__page {
  background-image: url("../assets/images/5.jpg");
  font-size: 20px;
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.login__block {
  min-width: 500px;
  background-color: var(--white);
  border-radius: 10px;

  h1 {
    text-align: center;
  }

  form {
    div {
      margin: 30px 30px;
      display: grid;
      gap: 5px;
    }
  }
}

.login__button {
  font-size: 16px;
  padding: 10px 10px;
  background: var(--red);
  color: white;
  border: solid 1px white;
  border-radius: 5px;

  &:hover {
    background: #9b3434;
    cursor: pointer;
  }
}

.login__input {
  font-size: 16px;
  border: solid 1px var(--black);
  border-radius: 5px;
  padding: 10px 10px;

  &:hover {
    border: solid 1px var(--black);
    outline: solid 1px var(--black);
  }

  &:focus-visible {
    outline: solid 1px var(--black);
  }

  &:valid {
    border-color: green;
  }
  &:invalid {
    border-color: red;
  }
}

.login__input.valid {
  border-color: green;
}

.login__input.invalid {
  border-color: red;
}


</style>