import { Registration } from './registration';

describe('Registration', () => {
  it('should create an instance', () => {
    expect(new Registration("test@test.com", "test-display-name", "password")).toBeTruthy();
  });
});
