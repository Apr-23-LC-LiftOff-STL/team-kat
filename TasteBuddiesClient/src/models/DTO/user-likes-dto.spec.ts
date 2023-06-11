import { UserLikesDTO } from './user-likes-dto';

describe('UserLikesDTO', () => {
  it('should create an instance', () => {
    expect(new UserLikesDTO('1','1', true)).toBeTruthy();
  });
});
