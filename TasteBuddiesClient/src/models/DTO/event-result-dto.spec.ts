import { EventResultDTO } from './event-result-dto';

describe('EventResultDTO', () => {
  it('should create an instance', () => {
    expect(new EventResultDTO(1,'1',['1'],1,{'1': 1})).toBeTruthy();
  });
});
